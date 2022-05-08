package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class coursedisplaykro extends AppCompatActivity {

    FirebaseDatabase db;
    ArrayAdapter adapter;

    ListView listView;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedisplaykro);
        getSupportActionBar().setTitle("Courses");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db=FirebaseDatabase.getInstance();
   //calling function datapro

        listView=(ListView)findViewById(R.id.displv);
        datalao();

        adapter=new ArrayAdapter<String>(coursedisplaykro.this,R.layout.coursedispsinglerow,arrayList);
        listView.setAdapter(adapter);
    }
    public void datalao(){
        arrayList=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().
                child("Student").child("stcourse")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds:snapshot.getChildren()){
                            String va=ds.getValue(String.class);
                            arrayList.add(va);
                        }
                        Log.i("dekhao"," "+arrayList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}