package com.example.mylogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class courseapply extends AppCompatActivity {
FloatingActionButton ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseapply);
        getSupportActionBar().setTitle("DASHBOARD");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    ft=findViewById(R.id.applybtnfloat);

    ft.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
              Intent g=new Intent(courseapply.this,MainActivity.class);
             startActivity(g);



        }
    });
    }
}