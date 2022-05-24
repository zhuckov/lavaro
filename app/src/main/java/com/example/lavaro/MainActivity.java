package com.example.lavaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonRead , buttonAdd , buttonClean ;
    EditText etMail , etPhone , etName , etSurname , etPassword;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

        buttonAdd = findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(this);
        buttonClean = findViewById(R.id.btnClean);
        buttonClean.setOnClickListener(this);
        buttonRead = findViewById(R.id.btnClean);
        buttonRead.setOnClickListener(this);

        etMail = findViewById(R.id.email);
        etName = findViewById(R.id.name);
        etPhone = findViewById(R.id.phone);
        etSurname= findViewById(R.id.surname);
        etPassword= findViewById(R.id.password);

    }


    @Override
    public void onClick(View v) {

        String name = etName.getText().toString() ;
        String surname  = etSurname.getText().toString() ;
        String phone = etPhone.getText().toString() ;
        String email = etMail.getText().toString() ;
        String password = etPassword.getText().toString() ;

        SQLiteDatabase database = dbHelper.getWritableDatabase() ;

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);

                database.insert(DBHelper.TABLE_WORKERS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_WORKERS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    int phoneIndex = cursor.getColumnIndex(DBHelper.KEY_PHONE);
                    int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD) ;
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(emailIndex) +
                                ", phone = " + cursor.getString(phoneIndex) +
                                ", password = " + cursor.getString(passwordIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;

            case R.id.btnClean:;
                database.delete(DBHelper.TABLE_WORKERS, null, null);
                break;
        }
        dbHelper.close();
    }
}