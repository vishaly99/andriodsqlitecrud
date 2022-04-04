package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView lv1;
    database g;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn = findViewById(R.id.backbtn);
        lv1 = findViewById(R.id.list);

         g = new database(getApplicationContext());

        Cursor c = g.viewdata();

        if(c.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"no data found",Toast.LENGTH_SHORT).show();
        }

        List<String> ls = new ArrayList<>();

        while(c.moveToNext())
        {
            ls.add(c.getString(0));
            ls.add(c.getString(1));
            ls.add(c.getString(2));
            ls.add(c.getString(3));

        }

        //for data show in listview
        ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,ls);
        lv1.setAdapter(adp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}