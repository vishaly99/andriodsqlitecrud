package com.example.crudapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText etid,etname,etcity,etphone;
    Button btnadd1,btndel,btnupd,btnview;
    database g;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etid = findViewById(R.id.txtempid);
        etname = findViewById(R.id.txtname);
        etcity = findViewById(R.id.txtcity);
        etphone = findViewById(R.id.txtphone);

        lv = findViewById(R.id.l1);

        btnadd1 = findViewById(R.id.btnadd);
        btndel = findViewById(R.id.btndelete);
        btnupd = findViewById(R.id.btnupdate);
        btnview = findViewById(R.id.btnview);

        g = new database(this);

        btnadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etid.getText().toString();
                String name = etname.getText().toString();
                String city = etcity.getText().toString();
                String phone = etphone.getText().toString();

                Boolean i = g.insertdata(id,name,city,phone);
                if(i==true)
                {
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"not success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Cursor c = g.viewdata();
//
//                if(c.getCount()==0)
//                {
//                    Toast.makeText(getApplicationContext(),"no data found",Toast.LENGTH_SHORT).show();
//                }
//
//                List<String> ls = new ArrayList<>();
//
//                while(c.moveToNext())
//                {
//                    ls.add(c.getString(0));
//                    ls.add(c.getString(1));
//                    ls.add(c.getString(2));
//                    ls.add(c.getString(3));
//
//                }
//
//                //for data show in listview
//                ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,ls);
//                lv.setAdapter(adp);
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String empid = etid.getText().toString();
                Boolean i = g.deletedata(empid);

                if(i == true)
                {
                    Toast.makeText(getApplicationContext(),"data deleted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"data not deleted",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnupd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String empid = etid.getText().toString();
                String empname = etname.getText().toString();
                String empcity = etcity.getText().toString();
                String empphone = etphone.getText().toString();

                Boolean i = g.updatedatad(empid,empname,empcity,empphone);

                if(i==true)
                {
                    Toast.makeText(getApplicationContext(),"data updated",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"data not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}