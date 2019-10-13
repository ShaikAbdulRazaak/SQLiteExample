package com.roger.iot20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.roger.iot20.database.TABLE_NAME;


public class MainActivity extends AppCompatActivity {
    EditText regNo;
    Button submit;
    CardView card;
    TextView nameIN, email, phone_number, departmentIN,cash;
    int i;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regNo = (EditText) findViewById(R.id.regNo);
        nameIN = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.emailId);
        phone_number = (TextView) findViewById(R.id.phNo);
        departmentIN = (TextView) findViewById(R.id.department);
        submit = (Button) findViewById(R.id.submit);
        cash=(TextView)findViewById(R.id.cash);
        card=(CardView)findViewById(R.id.card);
        db = new database(this);
        final SQLiteDatabase database = db.getReadableDatabase();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (regNo.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Registration Number", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String i = regNo.getText().toString();
                        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Registration_Number LIKE " + i + "", null);
                        if (cursor == null) {
                            Toast.makeText(MainActivity.this, "not found", Toast.LENGTH_SHORT).show();
                        }
                        int regdNo = cursor.getColumnIndex("Registration_Number");
                        int name = cursor.getColumnIndex("Name");
                        int mailId = cursor.getColumnIndex("MailId");
                        int phoneNo = cursor.getColumnIndex("PhoneNo");
                        int department = cursor.getColumnIndex("Department");
                        cursor.moveToFirst();
                        if(cursor.moveToFirst()){
                            //record exists
                            card.setVisibility(View.VISIBLE);
                        }else {
                            card.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                        }
                        while (cursor != null) {
                            nameIN.setText("Name: " + cursor.getString(name));
                            email.setText("Email ID: " + cursor.getString(mailId));
                            phone_number.setText("Phone Number: " + cursor.getString(phoneNo));
                            departmentIN.setText("Department: " + cursor.getString(department));
                            Log.i("Result", cursor.getString(name));
                            Log.i("Result", cursor.getString(mailId));
                            Log.i("Result", cursor.getString(phoneNo));
                            Log.i("Result", cursor.getString(department));
                            cursor.moveToNext();
                            //Toast.makeText(getApplicationContext(), "E-Cash Available:600", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
