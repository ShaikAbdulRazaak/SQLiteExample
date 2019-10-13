package com.roger.iot20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

    public class database extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "Data.db";
        public static final String TABLE_NAME = "payment_Data";
        public static final String COL1 = "Registration_Number";
        public static final String COL2 = "Name";
        public static final String COL3 = "MailId";
        public static final String COL4 = "PhoneNo";
        public static final String COL5 = "Department";

        public database(@Nullable Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(Registration_Number INTEGER primary key,Name TEXT,MailId TEXT,PhoneNo TEXT,Department TEXT)");
            insertData(11713898,"Abcdefg","cderfhada@gmail.com","7123456789","ECE",sqLiteDatabase);
        }
        public void insertData( double RegdNo,String Name,String MailId,String PhoneNo,String Department,SQLiteDatabase database){
            ContentValues contentValues=new ContentValues();
            contentValues.put("Registration_Number",RegdNo);
            contentValues.put("Name",Name);
            contentValues.put("MailId",MailId);
            contentValues.put("PhoneNo",PhoneNo);
            contentValues.put("Department",Department);
            database.insert(TABLE_NAME,null,contentValues);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

