package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class replyactivity extends AppCompatActivity {
    public static String sendname;
    RecyclerView recyclerView;
    ImageView img;
    EditText ed;
    public  static String recname1;
   public static String sendkey;
    String reckey;
    ArrayList<chatmodel> replyarray;
    replyadapter ra;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replyactivity);

        recyclerView = findViewById(R.id.replyrecycler);
        img = findViewById(R.id.imagereply);
        ed = findViewById(R.id.replyedit);
         recname1= getIntent().getStringExtra("rec");
       // auth = FirebaseAuth.getInstance();
        // sendname = auth.getUid().toString();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference replyref=firebaseDatabase.getReference("Student").child(discussionboardactivity.stid).child("name");
        replyref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.exists()){
                    sendname=snapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        Log.i("replynamedekhao2", " " + sendname);
        sendkey = sendname + recname1;
        reckey = recname1 + sendname;
        Log.i("replynamedekhao2", " " + sendname);
        replylao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ra = new replyadapter(replyarray, this,sendname);
        recyclerView.setAdapter(ra);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply = ed.getText().toString();
                if (!reply.isEmpty()) {
                    FirebaseDatabase.getInstance()
                        .getReference("Student").child("reply").
                            child(sendkey).push().setValue(new chatmodel(ed.getText().toString(), sendname))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    FirebaseDatabase.getInstance().getReference("Student").child("reply").
                                            child(reckey).push().setValue(new chatmodel(ed.getText().toString(), sendname));
                                }
                            });
                    ed.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),"enter message",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void replylao() {
        Log.i("sendkeyinfun",""+sendkey);
        replyarray = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("Student").child("reply").child(sendkey);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                replyarray.clear();
                if (snapshot.exists()) {
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        chatmodel m = ds.getValue(chatmodel.class);
                        m.setMessageid(ds.getKey());
                        replyarray.add(m);
                        Log.i("replyarray",""+replyarray);
                    }
                    Log.i("replyarray",""+replyarray);
                }
                Collections.reverse(replyarray);
                ra.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}