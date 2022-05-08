package com.example.mylogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class customchatadapter extends RecyclerView.Adapter {
ArrayList<chatmodel> arrayList;
Context context;
int send=1;
int rec=2;
    public customchatadapter(ArrayList<chatmodel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        if(viewType==send){
            View v=LayoutInflater.from(context).inflate(R.layout.discussionrow,parent,false);
            return new myholder(v);
        }
        else {
            View v=LayoutInflater.from(context).inflate(R.layout.recrow,parent,false);
            return new myrecholder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder ab=new AlertDialog.Builder(context);
                ab.setMessage("Delete this message!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance()
                                        .getReference("Student").child("message")
                                        .child(arrayList.get(position).getMessageid())
                                        .setValue(null);
                            }
                        }).setNegativeButton("No",null).show();

                return true;
            }
        });
        if(holder.getClass()==myholder.class){
            ((myholder) holder).user.setText(arrayList.get(position).getText());
            ((myholder) holder).text.setText(arrayList.get(position).getUser());
            ((myholder) holder).time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",arrayList.get(position).getTime()));
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i=new Intent(((myholder) holder).user.getContext(),replyactivity.class);
                   i.putExtra("rec",arrayList.get(position).getUser());
                   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(i);

               }
           });
        }
        else {
            ((myrecholder) holder).recuser.setText(arrayList.get(position).getText());
            ((myrecholder) holder).rectext.setText(arrayList.get(position).getUser());
            ((myrecholder) holder).rectime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",arrayList.get(position).getTime()));
             holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(((myrecholder) holder).recuser.getContext(),replyactivity.class);
                ii.putExtra("rec",arrayList.get(position).getUser());
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);

            }
        });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(arrayList.get(position).getText().equals(discussionboardactivity.stnamelo)){
            return send;
        }
        else{
            return rec;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class myholder extends RecyclerView.ViewHolder{
        TextView user;
        TextView time;
        TextView text;
       // Button sbtn;
        View myvi;
        public myholder(@NonNull  View itemView) {
            super(itemView);
            user=itemView.findViewById(R.id.muser);
            time=itemView.findViewById(R.id.mtime);
            text=itemView.findViewById(R.id.mtext);
           // sbtn=itemView.findViewById(R.id.sendbtn);
            myvi=itemView;
        }
    }
    class myrecholder extends RecyclerView.ViewHolder{
        TextView recuser;
        TextView rectime;
        TextView rectext;
        View recmyvi;
       // Button rbtn;

        public myrecholder(@NonNull View itemView) {
            super(itemView);
            recuser=itemView.findViewById(R.id.recuser);
            rectime=itemView.findViewById(R.id.rectime);
            rectext=itemView.findViewById(R.id.rectext);
         //   rbtn=itemView.findViewById(R.id.recbtn);
            recmyvi=itemView;

        }
    }
}
  /*              chatmodel cm=arrayList.get(position);
        @Override
        public boolean onLongClick(View v) {
            remove(cm);
            return true;
        }


    });

}
    public void remove(chatmodel cm) {
        AlertDialog.Builder ab=new AlertDialog.Builder(context);
        ab.setMessage("delete this message!")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int currentpos=arrayList.indexOf(cm);
                        arrayList.remove(currentpos);
                        notifyItemRemoved(currentpos);

                    }
                }).setNegativeButton("No",null);
        AlertDialog ad= ab.create();
        ad.show();


    }*/
