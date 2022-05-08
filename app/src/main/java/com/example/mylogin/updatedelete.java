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

public class updatedelete extends AppCompatActivity {
    EditText titlee;
    EditText desce;
    Button up;
    Button del;
    String recieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedelete);

        getSupportActionBar().setTitle("Notes");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent g=getIntent();
        recieve=getIntent().getStringExtra("id");
        titlee=findViewById(R.id.updatedeleteedittitle);
        desce=findViewById(R.id.updatedeleteeditdesc);
        titlee.setText(g.getStringExtra("title"));
        desce.setText(g.getStringExtra("desc"));

    }

    public void updatekro(View view) {

        Databaseclass dbc=new Databaseclass(this);
        String te=titlee.getText().toString();
        String td=desce.getText().toString();
        if(!TextUtils.isEmpty(te) && !TextUtils.isEmpty(td)) {
            boolean c = dbc.updatekro(recieve, te, td);
            if (c == true) {
                Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_SHORT).show();
                Intent wapis = new Intent(updatedelete.this, Notes.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(wapis);

            } else {

                Toast.makeText(getApplicationContext(), "update not success", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void deletekro(View view) {


        Databaseclass dbc = new Databaseclass(this);
        dbc.datadeletekro(recieve);
        Intent wapis = new Intent(updatedelete.this, Notes.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(wapis);
    }


}