package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class helpingmaterial extends AppCompatActivity {
String helpingid,ghelpingid;
String sphelp[];
ListView help;
TextView hhide;
ArrayAdapter helpadapter;
//helpadater hadp;
   public static String hc1;
    public static String hc2;
    public static String hc3;
    public static String hc4;
    public static String hc5;
    public static ArrayList<String> help1=new ArrayList<>();
    public static ArrayList<String> help2=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpingmaterial);
        getSupportActionBar().setTitle("HelpingMaterial");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ghelpingid=getIntent().getStringExtra("stid");
    sphelp=ghelpingid.split("@");
    helpingid=sphelp[0];
    help=findViewById(R.id.helpinglv);
    hhide=findViewById(R.id.hideh);
    helpdetail();
    helpadapter = new ArrayAdapter<>(helpingmaterial.this, R.layout.materialrow, help2);

    help.setAdapter(helpadapter);
    helpadapter.clear();
    help1.clear();
    Log.i("helpmain dekho", " " + help2);
        Log.i("help1"," "+hc1+" "+hc2+" "
                +hc3+" "+hc4+" "+hc5);

    help.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent1 = new Intent(helpingmaterial.this, helpmaterial2.class);
            intent1.putExtra("pos", position);
            intent1.putExtra("c1",hc1);
            intent1.putExtra("c2",hc2);
            intent1.putExtra("c3",hc3);
            intent1.putExtra("c4",hc4);
            intent1.putExtra("c5",hc5);
            Log.i("help3"," "+hc1+" "+hc2+" "
                    +hc3+" "+hc4+" "+hc5);

            startActivity(intent1);


            }
        });

    }

    public  void   helpdetail() {
        FirebaseDatabase.getInstance().getReference().
                child("Student").child(helpingid).child("courses")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists()) {
                           ss(snapshot);
                           helpset();
                       }
                       else{
                           hhide.setText("Select your course first");
                       }

                        //size=arrayList1.size();
                        //Log.i("size1"," "+size);
                        //Log.i("array1 dekho"," "+arrayList1);

                    }

                    public void ss(DataSnapshot snapshot){
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String va = ds.getValue(String.class);
                            help1.add(va);
                        }
                        hc1=help1.get(0);
                        hc2=help1.get(1);
                        hc3=help1.get(2);
                        hc4=help1.get(3);
                        hc5=help1.get(4);


                        Log.i("help2"," "+hc1+" "+hc2+" "
                                +hc3+" "+hc4+" "+hc5);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void helpset() {
        for (int i = 0; i < help1.size(); i++) {

            FirebaseDatabase.getInstance().getReference().
                    child("Student").child("helping").child("title").child(help1.get(i))
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            gg(snapshot);
                            Log.i("help2 dekho", " " + help2);
                        }

                        public void gg(DataSnapshot sp) {

                            String va = sp.getValue(String.class);
                            help2.add(va);
                            helpadapter.notifyDataSetChanged();

                            //Log.i("size2", " " + size);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        }


    }

//    class helpadater extends ArrayAdapter{
  //      ArrayList<String> arrayList;
    //   public helpadater(Context context,ArrayList<String> ar){
      //     super(context,R.layout.materialrow,R.id.helpintext,ar);
        //   arrayList=ar;
      // }

        //@NonNull
        //@Override
        //public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          //  LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            //View helprow=inflater.inflate(R.layout.materialrow,parent,false);
            //TextView helptext=helprow.findViewById(R.id.helpintext);
            //helptext.setText(arrayList.get(position));

            //return helprow;
        //}
    }

