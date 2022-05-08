package com.example.mylogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class replyadapter  extends RecyclerView.Adapter{
ArrayList<chatmodel> arrayL;
Context context;
String name;
int rec=2;
int send=1;

    public replyadapter(ArrayList<chatmodel> arrayList, Context context,String name) {
        this.arrayL = arrayList;
        this.context = context;
        this.name=name;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==send){
            View v= LayoutInflater.from(context).inflate(R.layout.replysendrow,parent,false);
            return new myholder(v);
        }
        else{
            View v=LayoutInflater.from(context).inflate(R.layout.replyrecrow,parent,false);
            return  new myrecholder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder ad=new AlertDialog.Builder(context);
                ad.setMessage("Delete this message!")
                        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id=replyactivity.sendkey;
                                FirebaseDatabase.getInstance().getReference("Student").child("reply")
                                        .child(id).child(arrayL.get(position).getMessageid()).setValue(null);

                            }
                        }).setNegativeButton("no",null).show();


                return true;
            }
        });

        if(holder.getClass()==myholder.class){
            ((myholder) holder).user.setText(arrayL.get(position).getText());
            ((myholder) holder).text.setText(arrayL.get(position).getUser());
            ((myholder) holder).time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",arrayL.get(position).getTime()));
        }
        else {
            ((myrecholder) holder).recuser.setText(arrayL.get(position).getText());
            ((myrecholder) holder).rectext.setText(arrayL.get(position).getUser());
            ((myrecholder) holder).rectime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",arrayL.get(position).getTime()));

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(!arrayL.get(position).getText().equals(name)){
            return send;
        }
        else{
            return rec;
        }
    }

    @Override
    public int getItemCount() {
        return arrayL.size();
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView user;
        TextView time;
        TextView text;

        View myvi;
        public myholder(@NonNull View itemView) {
            super(itemView);
            user=itemView.findViewById(R.id.replysenduser);
            time=itemView.findViewById(R.id.replysendtime);
            text=itemView.findViewById(R.id.repysendtext);
            myvi=itemView;
        }
    }
    class myrecholder extends RecyclerView.ViewHolder{
        TextView recuser;
        TextView rectime;
        TextView rectext;
        View recmyvi;


        public myrecholder(@NonNull View itemView) {
            super(itemView);
            recuser=itemView.findViewById(R.id.replyrecuser);
            rectime=itemView.findViewById(R.id.replyrectime);
            rectext=itemView.findViewById(R.id.replyrectext);
            recmyvi=itemView;

        }
    }
}
