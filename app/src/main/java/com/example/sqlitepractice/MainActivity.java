package com.example.sqlitepractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b1,b2,b3,b4;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.etname);
        e2=findViewById(R.id.etsurname);
        e3=findViewById(R.id.etmarks);
        e4=findViewById(R.id.etid);
        b1=findViewById(R.id.btnadd);
        b2=findViewById(R.id.btnshow);
        b3=findViewById(R.id.btnupdate);
        b4=findViewById(R.id.btndelete);
        databaseHelper=new DatabaseHelper(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=databaseHelper.insertData(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                if(b==true){
                    Toast.makeText(getApplicationContext(),"DATA INSERTED",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"INSERTION FAILED",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Cursor cursor=databaseHelper.getData();
                    if(cursor.getCount()==0){
                        Toast.makeText(getApplicationContext(),"NO DATA",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        StringBuffer s=new StringBuffer();
                        while (cursor.moveToNext()) {
                            s.append("ID : " + cursor.getString(0)+"\n");
                            s.append("NAME : " + cursor.getString(1)+"\n");
                            s.append("SURNAME : " + cursor.getString(2)+"\n");
                            s.append("MARKS : " + cursor.getString(3)+"\n\n");
                        }
                        b2show("DATA",s.toString());
                    }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b=databaseHelper.updateData(e4.getText().toString(),e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                if(b==true){
                    Toast.makeText(getApplicationContext(),"DATA UPDATED",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"DATA NOT UPDATED",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer i=databaseHelper.deleteData(e4.getText().toString());
                Toast.makeText(getApplicationContext(),"DATA DELETED ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void b2show(String s1,String s2){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(s1);
        builder.setMessage(s2);
        builder.show();
    }
}