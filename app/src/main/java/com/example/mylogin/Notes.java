package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Notes extends AppCompatActivity {
    ListView listView1;
    ArrayAdapter adapter;
    //String my[] = {"i am title 1", "i am title 2"};
    //String des[] = {"i am desc 1", "i am desc 2"};
    ArrayList<modelclass> arrayList;

    Databaseclass mdbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getSupportActionBar().setTitle("Notes");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listView1 = findViewById(R.id.list);
        datanikalklao();
        customadapter adapter = new customadapter(getApplicationContext(), R.layout.noterow,arrayList);
        listView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent goedit=new Intent(Notes.this,updatedelete.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                goedit.putExtra("id",arrayList.get(position).getId());
                goedit.putExtra("title",arrayList.get(position).getTitle());
                goedit.putExtra("desc",arrayList.get(position).getDesc());
                startActivity(goedit);
            }
        });


    }

    public void clcikToAdd(View view) {
        Intent farword=new Intent(Notes.this,takenotes.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(farword);

    }
    public void datanikalklao(){
        arrayList=new ArrayList();
        mdbc=new Databaseclass(this);
        Cursor cursor=null;
        cursor=mdbc.nikalo();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                arrayList.add(new modelclass(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"no record added yet",Toast.LENGTH_SHORT).show();
        }

    }

    class customadapter extends ArrayAdapter {
        ArrayList<modelclass> arrayList11;

        public customadapter(Context context, int noterow, ArrayList<modelclass> arrayList) {
            super(context, R.layout.noterow, R.id.titl, arrayList);
            this.arrayList11 = arrayList;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.noterow,parent,false);
            TextView t=row.findViewById(R.id.titl);
            TextView d=row.findViewById(R.id.desc);
            t.setText(arrayList11.get(position).getTitle());
            d.setText(arrayList11.get(position).getDesc());

            return row;
        }
    }
}