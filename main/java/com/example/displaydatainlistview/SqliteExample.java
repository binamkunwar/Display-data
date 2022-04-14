package com.example.displaydatainlistview;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SqliteExample extends AppCompatActivity {
    EditText edtId,edtName,edtAddress;
    Button btnInsert,btnSelect,btnUpdate,btnDelete;
    MyDbHelper myDbHelper;
    ListView listView;

    @Override
    protected void onCreate( Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.sqlite_example);

    myDbHelper= new MyDbHelper(this);

    edtId =findViewById(R.id.edtId);
    edtName =findViewById(R.id.edtName);
    edtAddress =findViewById(R.id.edtAddress);
    btnInsert= findViewById(R.id.btnInsert);
    btnUpdate= findViewById(R.id.btnUpdate);
    btnSelect= findViewById(R.id.btnSelect);
    btnDelete= findViewById(R.id.btnDelete);
    listView=findViewById(R.id.outputList);

    btnInsert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id=Integer.parseInt(edtId.getText().toString());
            String name=edtName.getText().toString();
            String address=edtAddress.getText().toString();

            myDbHelper.insertData(id,name,address);
            Toast.makeText(getApplicationContext(),"Data Inserted Successfully!", Toast.LENGTH_SHORT).show();

        }
    });

    btnSelect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArrayList<Integer> id=new ArrayList<>();
            ArrayList<String> name= new ArrayList<>();
            ArrayList<String> address= new ArrayList<>();

            Cursor cursor=myDbHelper.selectData();
            while (cursor.moveToNext()){
                id.add(cursor.getInt(0));
                name.add(cursor.getString(1));
                address.add(cursor.getString(2));
            }
            ListAdapter adapter= new ListAdapter(SqliteExample.this,id,name,address);
            listView.setAdapter(adapter);
        }
    });
    btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String id= edtId.getText().toString();
            String name= edtName.getText().toString();
            String address=edtAddress.getText().toString();

            myDbHelper.updateData(id,name,address);
            Toast.makeText(getApplicationContext(),"Data Updated SuccessFully!",Toast.LENGTH_SHORT).show();
        }
    });

    btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String id=edtId.getText().toString();

            myDbHelper.deleteData(id);
            Toast.makeText(getApplicationContext(),"Data Deleted Successfully!",Toast.LENGTH_SHORT).show();
        }
    });
    }
}
