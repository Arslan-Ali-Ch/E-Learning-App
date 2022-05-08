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
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static android.view.View.GONE;

public class proffesor extends AppCompatActivity {

   public static String idloo;
   String idloo2;
   String[] idloo3;
   ListView listView2;
   TextView thide;
   public static String course1;
    public static String course2;
    public static String course3;
    public static String course4;
    public static String course5;

    public static ArrayList<String> arrayList1=new ArrayList<>();
    public static ArrayList<String> arrayList2=new ArrayList<>();
    int size,size2;
    FirebaseDatabase db;
    prodetailholder hold;
    ArrayAdapter<String> myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_proffesor);

        getSupportActionBar().setTitle("ProfessorDetail");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listView2 = findViewById(R.id.prolv);
        thide=findViewById(R.id.hidep);

        idloo2 = getIntent().getStringExtra("stid");
        idloo3 = idloo2.split("@");
        idloo = idloo3[0];
        db = FirebaseDatabase.getInstance();
//calling function datapro
        prodetail();
      myadapter = new ArrayAdapter<>(proffesor.this, R.layout.professorsinglerow, arrayList2);
      listView2.setAdapter(myadapter);
      myadapter.clear();
      arrayList1.clear();
      Log.i("holder"," "+course1+" "+course2+" "
                +course3+" "+course4+" "+course5);

      listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Intent intent1 = new Intent(proffesor.this, professordetail.class);
                  intent1.putExtra("pos", position);
                  intent1.putExtra("c1",course1);
                  intent1.putExtra("c2",course2);
                  intent1.putExtra("c3",course3);
                  intent1.putExtra("c4",course4);
                  intent1.putExtra("c5",course5);
                  startActivity(intent1);
          }
      });

    }

    public  void   prodetail() {

        FirebaseDatabase.getInstance().getReference().
                child("Student").child(idloo).child("courses")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                           arrayList1.clear();
                            if(snapshot.exists()) {
                                ss(snapshot);
                                proset();

                                size = arrayList1.size();
                                Log.i("size1", " " + size);
                                Log.i("array1 dekho", " " + arrayList1);
                            }
                            else{
                                thide.setText("First select your courses");
                            }

                    }

                        public void ss(DataSnapshot snapshot){
                        for (DataSnapshot ds : snapshot.getChildren()) {
                                String va = ds.getValue(String.class);
                                arrayList1.add(va);
                            }
                            course1=arrayList1.get(0);
                            course2=arrayList1.get(1);
                            course3=arrayList1.get(2);
                            course4=arrayList1.get(3);
                            course5=arrayList1.get(4);

                            Log.i("holder1"," "+course1+" "+course2+" "
                                      +course3+" "+course4+" "+course5);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

}


    public void proset() {
        for (int i = 0; i < arrayList1.size(); i++) {

            FirebaseDatabase.getInstance().getReference().
                    child("Student").child("professor").child( arrayList1.get(i))
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                            gg(snapshot);
                        }
                        }

                        public void gg(DataSnapshot sp){
                            String va = sp.child("aaname").getValue(String.class);
                            String[] spva=va.split(":");
                            String vaget=spva[1];
                            arrayList2.add(vaget);
                            myadapter.notifyDataSetChanged();

                            Log.i("size2"," "+size);
                            Log.i("array2 dekho"," "+arrayList1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        }

    }


    //public void prosetd(){
      //  prodetailholder hold=new prodetailholder();
        //for(int i=0;i<arrayList1.size();i++){

          //  if(i==0){
            //    hold.setCourse1(arrayList1.get(i));
           // }
           // else if(i==1){
             //   hold.setCourse2(arrayList1.get(i));
           // }
           // else if(i==2){
             //   hold.setCourse3(arrayList1.get(i));
           // }
           // else if(i==3){
             //   hold.setCourse4(arrayList1.get(i));
          //  }
           // else if(i==4){
             //   hold.setCourse5(arrayList1.get(i));
           // }
       // }
       // Log.i("holder1"," "+hold.getCourse1()+" "+hold.getCourse2()+" "
         //       +hold.getCourse3()+" "+hold.getCourse4()+" "+hold.getCourse5());

    //}*/
}