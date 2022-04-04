package com.example.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname = "emp.db";

    public database(@Nullable Context context) {
        super(context, dbname , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "create table Employee(emp_id String primary key,emp_name text,emp_city text,emp_phone text )";
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Employee");
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String emp_id,String emp_name,String emp_city, String emp_phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("emp_id", emp_id);
        c.put("emp_name", emp_name);
        c.put("emp_city", emp_city);
        c.put("emp_phone", emp_phone);

        long r = db.insert("Employee",null,c);

        if(r==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewdata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Employee",null);
        return res;
    }

    public boolean deletedata(String emp_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from Employee where emp_id=?",new String[]{emp_id});
        if(cursor.getCount()>0)
        {
            long r = db.delete("Employee","emp_id=?", new String[]{emp_id});

            if(r==-1)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        else
        {
            return false;
        }
    }

    public boolean updatedatad(String emp_id,String emp_name,String emp_city, String emp_phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("emp_id",emp_id);
        c.put("emp_name",emp_name);
        c.put("emp_city",emp_city);
        c.put("emp_phone",emp_phone);

        Cursor cursor = db.rawQuery("select * from Employee where emp_id=?",new String[]{emp_id});

        if(cursor.getCount()>0)
        {
            long r = db.update("Employee",c,"emp_id=?", new String[]{emp_id});

            if(r==-1)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        else
        {
            return false;
        }
    }
}
