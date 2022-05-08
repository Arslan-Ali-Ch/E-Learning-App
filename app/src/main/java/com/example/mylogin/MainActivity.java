package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText t1,t2;
   public static String s1;
   public static String s2;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("LOGIN");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.stbtn);
        t1= (EditText) findViewById(R.id.stmail);
        t2= (EditText) findViewById(R.id.stpass);

    }
    public void loginkro(View view) {
        customprogressbar cpb=new customprogressbar(MainActivity.this);
        cpb.show();
        s1 = t1.getText().toString();
        s2 = t2.getText().toString();
        auth = FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(s1)) {
            t1.setError("Enter email first");
            cpb.dismiss();
        }
        if (TextUtils.isEmpty(s2) ) {
            t2.setError("Enter password first");
            cpb.dismiss();
        }
        else{
            auth.signInWithEmailAndPassword(s1, s2)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                cpb.dismiss();
                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            Intent it=new Intent(MainActivity.this,dashboard.class);
                            it.putExtra("stid",s1);
                            startActivity(it);
                            t1.setText("");
                                t2.setText("");
                            } else {
                                Toast.makeText(getApplicationContext(), "failed Check your network connection", Toast.LENGTH_LONG).show();
                                t1.setError("inavlid email pr password");
                                t2.setError("inavlid email pr password");
                                cpb.dismiss();

                            }
                        }
                    });
        }

    }

    @Override
    public void onBackPressed() {
        MainActivity.super.onBackPressed();
    }
}