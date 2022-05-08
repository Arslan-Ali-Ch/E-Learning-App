package com.example.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databaseclass extends SQLiteOpenHelper {
    Context context;
    static final String dbname="student";
    private static final String tablename="tbstudent";

    public Databaseclass(@Nullable Context context) {
        super(context, dbname, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creat="create table "+tablename+" (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT, des TEXT)";
        db.execSQL(creat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ tablename);
        onCreate(db);
    }

    public boolean insertkro(String title,String desc){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("title",title);
        cv.put("des",desc);
        long i=db.insert(tablename,null,cv);
        if(i==-1){
            return false;
        }
        else {
            return true;
        }

    }


    public Cursor nikalo(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+tablename;
        Cursor c=null;
        if(db!=null){
            c= db.rawQuery(query,null);
        }
        return c;
    }

    public void datadeletekro(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        String q=" DELETE FROM "+ tablename +" where id"+"=\""+id+"\";";
        db.execSQL(q);
    }

    public boolean updatekro(String id,String title,String desc){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("title",title);
        cv.put("des",desc);
        long i=db.update(tablename,cv,"id=?",new String[]{id});
        if(i==-1){
            return false;
        }
        else {
            return true;
        }

    }

}
