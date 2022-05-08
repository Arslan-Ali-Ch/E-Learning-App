package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class professordetail extends AppCompatActivity {
    ListView listView;
    ArrayAdapter myadapt;
    FirebaseDatabase db;

   static String proarray1;
    static String proarray12;
    static String proarray13;
    static String proarray14;
    static String proarray15;

    static ArrayList<String> proarray2= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professordetail);
        getSupportActionBar().setTitle("ProfessorDetail");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = FirebaseDatabase.getInstance();
        prodetailholder holder11=new prodetailholder();
        listView = findViewById(R.id.prodetaillv);
        proarray1=null;

        proarray1= getIntent().getStringExtra("c1");
        proarray12= getIntent().getStringExtra("c2");
        proarray13= getIntent().getStringExtra("c3");
        proarray14= getIntent().getStringExtra("c4");
        proarray15= getIntent().getStringExtra("c5");
        int pos=getIntent().getIntExtra("pos",-1);

        if(pos==0){

            Log.i("proArray1"," "+proarray1);
            FirebaseDatabase.getInstance().getReference().child("Student").child("professor")
                    .child(proarray1).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    proarray2.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String val = ds.getValue(String.class);
                            proarray2.add(val);
                        }
                        myadapt.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });


        }
        else if(pos==1){
            Log.i("proArray2"," "+proarray12);
            FirebaseDatabase.getInstance().getReference().child("Student").child("professor")
                    .child(proarray12).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    proarray2.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String val = ds.getValue(String.class);
                            proarray2.add(val);
                        }
                        myadapt.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });


        }
        else if(pos==2){
            Log.i("proArray3"," "+proarray13);

            FirebaseDatabase.getInstance().getReference().child("Student").child("professor")
                    .child(proarray13).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    proarray2.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String val = ds.getValue(String.class);
                            proarray2.add(val);
                        }
                        myadapt.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });

        }
        else if(pos==3){
            Log.i("proArray4"," "+proarray14);
            FirebaseDatabase.getInstance().getReference().child("Student").child("professor")
                    .child(proarray14).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    proarray2.clear();
                    if(snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String val = ds.getValue(String.class);
                            proarray2.add(val);
                        }
                        myadapt.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });


        }
        else if(pos==4){

            Log.i("proArray5"," "+proarray15);

            FirebaseDatabase.getInstance().getReference().child("Student").child("professor")
                    .child(proarray15).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    proarray2.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String val = ds.getValue(String.class);
                            proarray2.add(val);
                        }
                        myadapt.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });


        }
        myadapt=new ArrayAdapter(professordetail.this,R.layout.professordetailsinglerow,proarray2);
        listView.setAdapter(myadapt);
        myadapt.clear();
    }

}
