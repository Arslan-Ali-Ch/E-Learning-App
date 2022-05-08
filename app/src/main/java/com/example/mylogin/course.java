package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class course extends AppCompatActivity {
    public String getstid;
    public String sp[];
    public static String id;
    public  int i;
    mycustom adapter;
    ListView listView;
    ArrayList<coursemodel> ar;

    // Write a message to the database
    // getting firebase instance
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Courses");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_course);
        getstid = getIntent().getStringExtra("stid");
        sp = getstid.split("@");
        id = sp[0];

        // function to show data for listview
        customprogressbar cpb=new customprogressbar(course.this);
        cpb.show();
        data();
        cpb.dismiss();
        // listview id and setting adapter
        listView = (ListView) findViewById(R.id.lv1);
        adapter = new mycustom(getApplicationContext(), ar);
        listView.setAdapter(adapter);


    }

    //data to display in listview
    public void data() {
        ar = new ArrayList<coursemodel>();
        DatabaseReference myref = database
                .getReference("Student").child("stcourse");
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                coursemodel cmd = new coursemodel(value, false);

                ar.add(cmd);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // btn on click function


    public void registrkro(View view) {
        int j=0;

        coursemodel cmd = new coursemodel();
        // for writing data into firebase
        // Write a message to the database
        //to put data in mc table
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student")
                .child(id).child("courses");

// hashmap for mc table
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<coursemodel> cary = adapter.ar;

        for ( i = 0; i < cary.size(); i++) {

            cmd = cary.get(i);
            if (cmd.isCheck())
            {
                i++;
                hashMap.put("course" + i, "" + cmd.getCoursetitle());
                i--;
                j++;
            }

        }
        if(j!=cary.size()){
            Toast.makeText(getApplicationContext(),
                    "select all courses "
                    , Toast.LENGTH_LONG).show();
        }
        if(j== cary.size()){
            myRef.setValue(hashMap);
            Toast.makeText(getApplicationContext(),
                    "you select " + hashMap
                    , Toast.LENGTH_LONG).show();
            Intent it = new Intent(course.this, courseapply.class);
            startActivity(it);
        }
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference REF=db.getReference().child("Student").child(id);
        REF.child("val").setValue("true");

    }



    // custom adapter class

    class mycustom extends ArrayAdapter {
        ArrayList<coursemodel> ar;

        public mycustom(Context context, ArrayList<coursemodel> ar) {
            super(context, R.layout.singlrow, R.id.ch1, ar);
            this.ar = ar;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.singlrow, parent, false);

            //getting refrence
            TextView t = row.findViewById(R.id.tv);
            coursemodel cm = ar.get(position);
            t.setText(cm.getCoursetitle());
            //getting chechbox refrence
            CheckBox ch = row.findViewById(R.id.ch1);
            ch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox c = (CheckBox) v;
                    coursemodel cmd = new coursemodel();
                    cmd = ar.get(position);
                    if (c.isChecked()) {
                        Toast.makeText(getApplicationContext(),
                                "you select"+cmd.getCoursetitle()
                                , Toast.LENGTH_SHORT).show();
                        cmd.setCheck(true);

                    }
                    else{

                        Toast.makeText(getApplicationContext(),
                                "you unselect " + cmd.getCoursetitle()
                                , Toast.LENGTH_SHORT).show();
                    }

                }
            });
            return row;
        }
    }



}