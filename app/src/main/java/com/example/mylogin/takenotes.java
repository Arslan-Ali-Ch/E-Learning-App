package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class takenotes extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    Button save;
    Databaseclass dbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takenotes);

        getSupportActionBar().setTitle("Notes");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        save=findViewById(R.id.save);
        editText1=findViewById(R.id.titleedit);
        editText2=findViewById(R.id.titledesc);


    }

    public void clickToSave(View view) {
        String s1=editText1.getText().toString();
        String s2=editText2.getText().toString();
        if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)){
            dbc=new Databaseclass(this);
            boolean c=dbc.insertkro(s1,s2);
            if(c==true){
                Intent back=new Intent(takenotes.this,Notes.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
                Toast.makeText(getApplicationContext(),"Data Added",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"cannot add data ",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Enter title and description",Toast.LENGTH_SHORT).show();
        }
    }
}