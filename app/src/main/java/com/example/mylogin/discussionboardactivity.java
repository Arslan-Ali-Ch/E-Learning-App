package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class discussionboardactivity extends AppCompatActivity {
ImageView img;
EditText et;
RecyclerView chatlv;
static String stid;
String gstid;
String spl[];
public static String stnamelo;
 ArrayList<chatmodel> chatarray;

customchatadapter cca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussionboardactivity);
        getSupportActionBar().setTitle("DiscussionBoard");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        img=findViewById(R.id.imagechat);
        et=findViewById(R.id.chatedit);
        chatlv=findViewById(R.id.chatlist);
        gstid=getIntent().getStringExtra("stid");
        spl=gstid.split("@");
        stid=spl[0];
        stdata();
        getchat();
        Studentclass s=new Studentclass();
        Log.i("namedekhao"," "+stnamelo);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        chatlv.setLayoutManager(linearLayoutManager);
        chatlv.setHasFixedSize(true);
        cca=new customchatadapter(chatarray, this);
        chatlv.setAdapter(cca);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText)findViewById(R.id.chatedit);
                String abc=input.getText().toString();
                if(TextUtils.isEmpty(abc)) {
                    Toast.makeText(getApplicationContext(),"Enter your message",Toast.LENGTH_SHORT).show();

                }
                else{
                    FirebaseDatabase.getInstance()
                            .getReference("Student").child("message").push().setValue(new chatmodel(input.getText().toString(), stnamelo));
                    input.setText("");

                }
                }
        });

    }




    public void stdata(){
        Studentclass stc=new Studentclass();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference reff=db.getReference("Student").child(stid);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                stc.setName(snapshot.child("name").getValue().toString());
                Log.i("stname"," "+stc.getName());
                stnamelo=stc.getName();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

public void getchat(){
    chatarray=new ArrayList<>();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference refe=db.getReference("Student").child("message");
        refe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                chatarray.clear();
                if(snapshot.exists()){
                    for(DataSnapshot ds:snapshot.getChildren()){
                        chatmodel cm=ds.getValue(chatmodel.class);
                        cm.setMessageid(ds.getKey());
                        chatarray.add(cm);
                    }
                    Log.i("chatarray",""+chatarray);
                    Collections.reverse(chatarray);
                    cca.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
}

/*
class customchat extends ArrayAdapter{
        ArrayList<chatmodel> arrayList;
        public customchat(Context context,ArrayList<chatmodel> arrayList){
            super(context,R.layout.discussionrow,R.id.mtext,arrayList);
            this.arrayList=arrayList;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View cv=inflater.inflate(R.layout.discussionrow,parent,false);

        TextView user=cv.findViewById(R.id.muser);
        TextView time=cv.findViewById(R.id.mtime);
        TextView text=cv.findViewById(R.id.mtext);
        chatmodel cm=arrayList.get(position);
        user.setText(cm.getText());
        time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                cm.getTime()));
        text.setText(cm.getUser());
        return  cv;
    }
}
*/
}