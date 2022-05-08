package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class helpmaterial2 extends AppCompatActivity {
    String hc21, hc22, hc23, hc24, hc25;
    int posit;
    public static String urllo;
    public static String titlelo;
    ListView hlv2;
  public static   ArrayList<help2model> helparray=new ArrayList<>();
customhelp2 cushelp;

    //help2adapter h2adapter;
    FirebaseDatabase helpdb = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpmaterial2);
        getSupportActionBar().setTitle("HelpingMaterial");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        posit = getIntent().getIntExtra("pos", 0);
        hc21 = getIntent().getStringExtra("c1");
        hc22 = getIntent().getStringExtra("c2");
        hc23 = getIntent().getStringExtra("c3");
        hc24 = getIntent().getStringExtra("c4");
        hc25 = getIntent().getStringExtra("c5");
        hlv2 = findViewById(R.id.help2lv);
        //  pdfdata();

        cushelp=new customhelp2(this,helparray);
        hlv2.setAdapter(cushelp);

      //  Log.i("malluu", " " + hc21 + "  " + hc22 + "  " + hc23 + "  " + hc24 + " ");
        if (posit == 0) {
            DatabaseReference helpref = helpdb.getReference("Student").child("helping").child(hc21);
            helpref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    helparray.clear();
                    if (snapshot.exists()) {
                        {
                            pdflo(snapshot);
                             cushelp.notifyDataSetChanged();
                        }


                    }
                }
                public void pdflo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()){
                        help2model help2 = snapshot.child(String.valueOf(i)).getValue(help2model.class);
                        helparray.add(help2);
                        i++;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
//2nd if
        if (posit == 1) {
            DatabaseReference helpref = helpdb.getReference("Student").child("helping").child(hc22);
            helpref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    helparray.clear();
                    if (snapshot.exists()) {
                        {
                            pdflo(snapshot);
                            cushelp.notifyDataSetChanged();
                        }


                    }
                }

                public void pdflo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()){
                        help2model help2 = snapshot.child(String.valueOf(i)).getValue(help2model.class);
                        helparray.add(help2);
                        i++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

        //3rd if

        if (posit == 2) {
            DatabaseReference helpref = helpdb.getReference("Student").child("helping").child(hc23);
            helpref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    helparray.clear();
                    if (snapshot.exists()) {
                        {
                            pdflo(snapshot);
                            cushelp.notifyDataSetChanged();
                        }


                    }
                }

                public void pdflo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()){
                        help2model help2 = snapshot.child(String.valueOf(i)).getValue(help2model.class);
                        helparray.add(help2);
                        i++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

        //4th if
        if (posit == 3) {
            DatabaseReference helpref = helpdb.getReference("Student").child("helping").child(hc24);
            helpref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    helparray.clear();
                    if (snapshot.exists()) {
                        {
                        pdflo(snapshot);
                            cushelp.notifyDataSetChanged();
                        }


                    }
                }

                public void pdflo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()){
                        help2model help2 = snapshot.child(String.valueOf(i)).getValue(help2model.class);
                        helparray.add(help2);
                        i++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

        //5th if
        if (posit == 4) {
            DatabaseReference helpref = helpdb.getReference("Student").child("helping").child(hc25);
            helpref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    helparray.clear();
                    if (snapshot.exists()) {
                        {
                            pdflo(snapshot);
                            cushelp.notifyDataSetChanged();
                        }


                    }
                }

                public void pdflo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()){
                        help2model help2 = snapshot.child(String.valueOf(i)).getValue(help2model.class);
                        helparray.add(help2);
                        i++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

        //onitem
        hlv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                help2model h2m=new help2model();
                Intent pdfgo=new Intent(helpmaterial2.this,showhelpingmaterial.class);
                pdfgo.putExtra("pdfpos",position);
                startActivity(pdfgo);
            }
        });

    }






 class customhelp2 extends ArrayAdapter{
        ArrayList<help2model> arrayList;
        public  customhelp2(Context context, ArrayList<help2model> arrayList){
            super( context,R.layout.help2singlerow,R.id.pdftext,arrayList);
            this.arrayList=arrayList;
        }

     @NonNull
     @Override
     public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         LayoutInflater inflater=LayoutInflater.from(parent.getContext());
         View view=inflater.inflate(R.layout.help2singlerow,parent,false);

         TextView tv=view.findViewById(R.id.pdftext);
         help2model h2=arrayList.get(position);

         tv.setText(h2.getTitle());
         return view;
        }
 }

}