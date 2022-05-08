package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.mylogin.R.drawable.*;

public class dashboard extends AppCompatActivity {
ListView lv;
public static String stid;
public static String check;
String splitee[];
String sutid;
//String check2;
String title[]={"Course Selection","Professor Detail","Add Notes"
        ,"Watch Lecture videos","Post Messages","Helping Material"};
int img[]={R.drawable.course,R.drawable.user, notes
        , video, discussion, material};
FloatingActionButton fbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("DASHBOARD");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        stid=getIntent().getStringExtra("stid");
        splitee=stid.split("@");
        sutid=splitee[0];
        fbtn=findViewById(R.id.btnfloat);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ab=new AlertDialog.Builder(com.example.mylogin.dashboard.this);
                ab.setMessage("Are you sure to Logout")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                com.example.mylogin.dashboard.super.onBackPressed();
                              //  Intent g=new Intent(com.example.mylogin.dashboard.this,MainActivity.class);
                               // startActivity(g);
                            }
                        })
                        .setNegativeButton("No",null);

                AlertDialog ad=ab.create();
                ad.show();



            }
        });
        checklo();
        lv=(ListView)findViewById(R.id.dlv);
        myadapter m=new myadapter(this,title,img);
        lv.setAdapter(m);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference dref=db.getReference("Student").child(sutid);
                    dref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            if (snapshot.exists()){
                            check = snapshot.child("val").getValue().toString();
                            Log.i("check1kro", " " + check);
                            if(!check.equals("true")) {
                                Intent c = new Intent(dashboard.this, course.class);
                                c.putExtra("stid", stid);
                                startActivity(c);
                            }
                            else if(check.equals("true")){
                                Intent c2 = new Intent(dashboard.this, coursedisplaykro.class);
                                // c2.putExtra("stid", stid);
                                startActivity(c2);

                            }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                if(position==1){
                    Intent c=new Intent(dashboard.this,proffesor.class);
                    c.putExtra("stid",stid);
                    startActivity(c);
                }
                if(position==2){
                    Intent c=new Intent(dashboard.this,Notes.class);
                    c.putExtra("stid",stid);
                    startActivity(c);
                }
                if(position==3){
                    Intent c=new Intent(dashboard.this,video.class);
                    c.putExtra("stid",stid);
                    startActivity(c);
                }
                if(position==4){
                    Intent c=new Intent(dashboard.this,discussionboardactivity.class);
                    c.putExtra("stid",stid);
                    startActivity(c);
                }
                if(position==5){
                    Intent c=new Intent(dashboard.this,helpingmaterial.class);
                    c.putExtra("stid",stid);
                    startActivity(c);
                }

            }
        });

    }
    public void checklo(){


    }





    class myadapter extends ArrayAdapter<String>{
        Context context;
        String t[];
        int img[];
        //constructor
        public myadapter(Context c,String t[],int img[]){
            super(c,R.layout.row,R.id.dtext,t);
            context=c;
            this.t=t;
            this.img=img;

        }
        //method getview


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View view=inflater.inflate(R.layout.row,parent,false);
            TextView tv=view.findViewById(R.id.dtext);
            ImageView im=view.findViewById(R.id.dimg);
            tv.setText(t[position]);
            im.setImageResource(img[position]);
            return view;
        }
    }
//

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab=new AlertDialog.Builder(com.example.mylogin.dashboard.this);
        ab.setMessage("Are you sure to exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // com.example.mylogin.dashboard.super.onBackPressed();

                        Toast.makeText(getApplicationContext(), "go to lougout ", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No",null);

                AlertDialog ad=ab.create();
                ad.show();
    }
}