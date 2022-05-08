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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class video2 extends AppCompatActivity {
String v21,v22,v23,v24,v25;
int vpos;
public static String video2lo;
public static ArrayList<video2model> video2array=new ArrayList<>();
video2customadapter customv;
FirebaseDatabase videodb = FirebaseDatabase.getInstance();
    ListView video2list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);

        getSupportActionBar().setTitle("VideoLectures");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    vpos=getIntent().getIntExtra("pos",0);
        v21=getIntent().getStringExtra("v1");
        v22=getIntent().getStringExtra("v2");
        v23=getIntent().getStringExtra("v3");
        v24=getIntent().getStringExtra("v4");
        v25=getIntent().getStringExtra("v5");
        video2list=findViewById(R.id.video2lv);
        Log.i("videodekao"," "+v21+" "+v22+" "+v23+" "+v24+" "+v25+" ");

        customv=new video2customadapter(this,video2array);
        video2list.setAdapter(customv);

        if (vpos == 0) {
            DatabaseReference videoref = videodb.getReference("Student").child("video").child(v21);
            videoref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    video2array.clear();
                    if (snapshot.exists()) {
                        {
                            datalo(snapshot);
                           // video2model vmd = snapshot.getValue(video2model.class);
                           // video2array.add(vmd);
                           // video2lo=vmd.getVideourl();
                         // Log.i("vurldekhao",video2lo);
                          //  Log.i("vmddekhao",vmd.getTitle());
                           // Log.i("vmddekhao",vmd.getVideourl());
                            customv.notifyDataSetChanged();
                        }


                    }
                }
                public void datalo(DataSnapshot snapshot){

                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        video2model vmd = snapshot.child(String.valueOf(i)).getValue(video2model.class);
                        video2array.add(vmd);
                        i++;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
//2nd if

        if (vpos == 1) {
            DatabaseReference videoref = videodb.getReference("Student").child("video").child(v22);

             videoref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    video2array.clear();
                    if (snapshot.exists()) {
                        {
                            datalo(snapshot);

                           // Log.i("vurldekhao",video2lo);
  //                          Log.i("vmddekhao",vmd.getTitle());
//                            Log.i("vmddekhao",vmd.getVideourl());
                            customv.notifyDataSetChanged();
                        }


                    }
                }
                public void datalo(DataSnapshot snapshot){

                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        video2model vmd = snapshot.child(String.valueOf(i)).getValue(video2model.class);
                        video2array.add(vmd);
                        i++;
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }
//3rd if

        if (vpos == 2) {
            DatabaseReference videoref = videodb.getReference("Student").child("video").child(v23);
            videoref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                  //  video2model vmd = new video2model();
                    video2array.clear();
                    if (snapshot.exists()) {
                        {
                            datalo(snapshot);
                          //  video2model vmd = snapshot.getValue(video2model.class);
                          //  video2array.add(vmd);
                           // video2lo=vmd.getVideourl();
                           // Log.i("vurldekhao",video2lo);
                            //Log.i("vmddekhao",vmd.getTitle());
                           // Log.i("vmddekhao",vmd.getVideourl());
                            customv.notifyDataSetChanged();
                        }


                    }
                }
                public void datalo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        video2model vmd = snapshot.child(String.valueOf(i)).getValue(video2model.class);
                        video2array.add(vmd);
                        i++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

//4th if

        if (vpos == 3) {
            DatabaseReference videoref = videodb.getReference("Student").child("video").child(v24);
            videoref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                 //   video2model vmd = new video2model();
                    video2array.clear();
                    if (snapshot.exists()) {
                        {
                            datalo(snapshot);
                           // Log.i("vurldekhao",video2lo);
                           // Log.i("vmddekhao",vmd.getTitle());
                           // Log.i("vmddekhao",vmd.getVideourl());
                            customv.notifyDataSetChanged();
                        }


                    }
                }
                public void datalo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        video2model vmd = snapshot.child(String.valueOf(i)).getValue(video2model.class);
                        video2array.add(vmd);
                        i++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

//5th if

        if (vpos == 4) {
            DatabaseReference videoref = videodb.getReference("Student").child("video").child(v25);
            videoref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                  //  video2model vmd = new video2model();
                    video2array.clear();
                    if (snapshot.exists()) {
                        {
                            datalo(snapshot);
                           // video2model vmd = snapshot.getValue(video2model.class);
                           // video2array.add(vmd);
                            //video2lo=vmd.getVideourl();
                           // Log.i("vurldekhao",video2lo);
                           // Log.i("vmddekhao",vmd.getTitle());
                           // Log.i("vmddekhao",vmd.getVideourl());
                            customv.notifyDataSetChanged();
                        }


                    }
                }
                public void datalo(DataSnapshot snapshot){
                    int i=1;
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        video2model vmd = snapshot.child(String.valueOf(i)).getValue(video2model.class);
                        video2array.add(vmd);
                        i++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }



        video2list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent gotoshowv=new Intent(video2.this,showvideo.class);
                video2model v= video2array.get(position);
             String s= v.getVideourl();
             gotoshowv.putExtra("vurl",s);
             gotoshowv.putExtra("vpos",position);
             startActivity(gotoshowv);
            }
        });
    }



    class video2customadapter extends ArrayAdapter{
        ArrayList<video2model> arrayList;
        public video2customadapter(Context context,ArrayList<video2model> arrayList){
            super(context,R.layout.video2sinnglerow,R.id.video2text,arrayList);
            this.arrayList=arrayList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View v=inflater.inflate(R.layout.video2sinnglerow,parent,false);

            TextView vt=v.findViewById(R.id.video2text);
            video2model v2m=arrayList.get(position);
            vt.setText(v2m.getTitle());
            return  v;
        }
    }
}