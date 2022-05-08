package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class video extends AppCompatActivity {
    String videoid,gvideoid;
    String spvideo[];
    ListView videolv;
    TextView vhide;
    ArrayAdapter videoadapter;
    //helpadater hadp;
    int j;
    public static String v1;
    public static String v2;
    public static String v3;
    public static String v4;
    public static String v5;
    public static ArrayList<String> videoarray1=new ArrayList<>();
    public static ArrayList<String> videoarray2=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getSupportActionBar().setTitle("VideoLectures");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gvideoid=getIntent().getStringExtra("stid");
        spvideo=gvideoid.split("@");
        videoid=spvideo[0];
        videolv=findViewById(R.id.videolv);
        vhide=findViewById(R.id.hidev);
        videodetail();
        videoadapter = new ArrayAdapter<>(video.this, R.layout.videosinglerow, videoarray2);

        videolv.setAdapter(videoadapter);
        videoadapter.clear();
        videoarray1.clear();
        videolv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent govideo=new Intent(video.this,video2.class);
                govideo.putExtra("v1",v1);
                govideo.putExtra("v2",v2);
                govideo.putExtra("v3",v3);
                govideo.putExtra("v4",v4);
                govideo.putExtra("v5",v5);
                govideo.putExtra("pos",position);
                startActivity(govideo);
            }
        });


    }

    public  void   videodetail() {
        FirebaseDatabase.getInstance().getReference().
                child("Student").child(videoid).child("courses")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            ss(snapshot);
                            videoset();
                        }
                        else{
                            vhide.setText("Select your course first");
                        }
                    }

                    public void ss(DataSnapshot snapshot){
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String va = ds.getValue(String.class);
                            videoarray1.add(va);
                        }
                        v1=videoarray1.get(0);
                        v2=videoarray1.get(1);
                        v3=videoarray1.get(2);
                        v4=videoarray1.get(3);
                        v5=videoarray1.get(4);


                        Log.i("help2"," "+v1+" "+v2+" "
                                +v3+" "+v4+" "+v5);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void videoset() {

        for (int i = 0; i < videoarray1.size(); i++) {
              j=i;
            FirebaseDatabase.getInstance().getReference().
                    child("Student").child("video").child("title").child(videoarray1.get(i))
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            gg(snapshot);
                            Log.i("help2 dekho", " " + videoarray2);
                        }

                        public void gg(DataSnapshot sp) {

                            String va = sp.getValue(String.class);
                            videoarray2.add(va);
                            videoadapter.notifyDataSetChanged();

                            //Log.i("size2", " " + size);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            j++;
        }


    }



}