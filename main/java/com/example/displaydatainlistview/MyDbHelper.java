package com.example.displaydatainlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="mydb";

    public MyDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery="CREATE TABLE mytable(id INTEGER PRIMARY KEY,name TEXT,address TEXT)";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);

    }

    public Cursor selectData(){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT * FROM mytable ";
        Cursor cursor=db.rawQuery(query,null );
        return cursor;
    }

    public void updateData(String id, String name, String address) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address",address);
        db.update("mytable", contentValues, "id=?",new  String[]{id});
        db.close();
    }

    public void deleteData(String id) {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("mytable","id=?",new String[]{id});

    }

    public void insertData(int id, String name, String address) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("address",address);

        db.insert("mytable",null,contentValues);
        db.close();

    }
}
