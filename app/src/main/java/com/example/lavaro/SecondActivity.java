package com.example.lavaro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear , btnToFirst;
    EditText etName, etEmail , etPhone , etPassword , etJob , etSalary , etInfo , etOrganiation;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employers);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnToFirst = (Button) findViewById(R.id.toFirst);
        btnToFirst.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSalary = (EditText) findViewById(R.id.etSalary);
        etJob = (EditText) findViewById(R.id.etJob);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etInfo = (EditText) findViewById(R.id.etInfo);
        etOrganiation = (EditText) findViewById(R.id.etOrganization);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String job = etJob.getText().toString();
        String salary = etSalary.getText().toString();
        String password = etPassword.getText().toString();
        String organization = etOrganiation.getText().toString();
        String info = etInfo.getText().toString();




        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.toFirst:
                Intent i ;
                i = new Intent(this , MainActivity.class) ;
                startActivity(i);
                break;



            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);
                contentValues.put(DBHelper.KEY_JOB, job);
                contentValues.put(DBHelper.KEY_SALARY, salary );
                contentValues.put(DBHelper.KEY_PHONE, phone );
                contentValues.put(DBHelper.KEY_PASSWORD, password );
                contentValues.put(DBHelper.KEY_ORGANIZATION, organization);
                contentValues.put(DBHelper.KEY_INFO, info );


                database.insert(DBHelper.TABLE_CONTACTS_EMP, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS_EMP, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    int jobIndex = cursor.getColumnIndex(DBHelper.KEY_JOB);
                    int salaryIndex = cursor.getColumnIndex(DBHelper.KEY_SALARY);
                    int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                    int phoneIndex = cursor.getColumnIndex(DBHelper.KEY_PHONE);
                    int organizationIndex = cursor.getColumnIndex(DBHelper.KEY_ORGANIZATION);
                    int infoIndex = cursor.getColumnIndex(DBHelper.KEY_INFO);


                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", password = " + cursor.getString(passwordIndex) +
                                ", job = " + cursor.getString(jobIndex) +
                                ", salary = " + cursor.getString(salaryIndex) +
                                ", phone = " + cursor.getString(phoneIndex) +
                                ", organization = " + cursor.getString(organizationIndex) +
                                ", info = " + cursor.getString(infoIndex) +
                                ", email = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS_EMP, null, null);
                break;
        }
        dbHelper.close();
    }
}