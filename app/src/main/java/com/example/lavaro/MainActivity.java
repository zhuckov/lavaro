package com.example.lavaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear , btnAddWE , btnEmpAdd ; // test  // test  // test  // test  // test  // test  // test
    EditText etName, etSurname ,etEmail , etPlace , etYear , etAboutYou , etCity , etPhone , etEducationPlace , etPassword , etSalary, etJob , etEmp; // test  // test  // test  // test  // test  // test  // test  // test
    String allWE = "";
 /*   String employersList = "" ; */   // test  // test  // test  // test  // test  // test  // test  // test  // test  // test  // test  // test  // test
    DBHelper dbHelper;
    String phone, email ;
    Spinner education ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            // test  // test  // test  // test  // test  // test  // test  // test  // test

        /*
        btnEmpAdd = (Button) findViewById(R.id.btnAddEmp);
        btnEmpAdd.setOnClickListener(this); // test  */
        // test  // test  // test  // test  // test  // test  // test  // test  // test  // test

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnAddWE = (Button) findViewById(R.id.btnAddWE);
        btnAddWE.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etPassword = (EditText) findViewById(R.id.etPassword) ;
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPlace = (EditText) findViewById(R.id.etPlace);
        etSalary = (EditText) findViewById(R.id.etSalary);
        etJob = (EditText) findViewById(R.id.etJob);
        etYear = (EditText) findViewById(R.id.etYear);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etCity = (EditText) findViewById(R.id.etCity);
        etAboutYou = (EditText) findViewById(R.id.etAboutYou);
        etEducationPlace = (EditText) findViewById(R.id.educationPlace);
        education =  (Spinner) findViewById(R.id.spEducation) ;


       /* etEmp = (EditText) findViewById(R.id.etEmployer); */ // test  // test  // test  // test  // test  // test


        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.education,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        education.setAdapter(adapter);


        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String password = etPassword.getText().toString();
        String city = etCity.getText().toString();
        String salary = etSalary.getText().toString();
        String job = etJob.getText().toString();
        String aboutUser = etAboutYou.getText().toString();
        String educationPlace = etEducationPlace.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        switch (v.getId()) { // С помощью этого обалденного условия смотрим на какую кнопку был нажатие

          /* case R.id.btnAddEmp: // test  // test  // test  // test  // test  // test  // test  // test  // test  // test  // test
                String employer = etEmp.getText().toString(); // test
                employersList +=  employer + "|"; // test
                // test
                break; */  // test  // test  // test  // test  // test  // test  // test  // test  // test  // test  // test  // test


            case R.id.btnAddWE:
                String place = etPlace.getText().toString();
                String years = etYear.getText().toString();
                String spec =  place + "|" + years + "|" ; // формируем строку
                allWE += spec ; // добавляем к той что для БД
                etYear.setText("");
                etPlace.setText("");
                Toast toast = Toast.makeText(this , "Опыт работы добавлен" , Toast.LENGTH_SHORT );
                toast.show();
                break;

            case R.id.btnAdd:

                if (etEmail.getText().toString().matches(".+@.+\\..+")) {  // Проверка строки на то является ли строка email адресом , я очень плохую регулярку сделал но она работает
                    email = etEmail.getText().toString();
                }else{
                    Toast toast1 = Toast.makeText(this , "Почта введена неверно" , Toast.LENGTH_LONG ); // Тут следует продумать что делать если email передан неверно
                    email = "Почта не указана.";
                    toast1.show();
                }
                if (etPhone.getText().toString().matches("^(\\+7|8)[0-9]{10,11}")) { //  Тоже регулярка не очень нужно будет по хорошему другую сделать ( скорее всего я сделаю ) , она тоже рабочая но не все ситуации для записи номера обрабатывает например 8 900 015 23 46 или  8 (904) 477 43 43  или  8-904-319-32-34        .
                    phone = etPhone.getText().toString();

                }else{ // Если номер не прошел по регулярке , значение phone ( email тоже ) попадут в БД с тестом "Телефон не указан." поэтому else нужно дописать тосты бы оставил с телефона выглядит прикольно
                    Toast toast1 = Toast.makeText(this , "Номер введен неверно" , Toast.LENGTH_LONG );
                    phone = "Телефон не указан.";
                    toast1.show();
                }
                contentValues.put(DBHelper.KEY_MAIL, email);
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_SURNAME, surname);
                contentValues.put(DBHelper.KEY_PASSWORD, password);
                contentValues.put(DBHelper.KEY_JOB, job);
                contentValues.put(DBHelper.KEY_SALARY, salary);
                contentValues.put(DBHelper.KEY_PHONE, phone);
                contentValues.put(DBHelper.KEY_CITY, city);
                contentValues.put(DBHelper.KEY_ABOUT, aboutUser);
                contentValues.put(DBHelper.KEY_EDUCATION, education.getSelectedItem().toString());
                contentValues.put(DBHelper.KEY_EDUCATION_PLACE, educationPlace);
                contentValues.put(DBHelper.KEY_WORKEXP, allWE);


             /*   contentValues.put(DBHelper.KEY_EMPLOYERS_LIST, employersList); */  // test





                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int surnameIndex = cursor.getColumnIndex(DBHelper.KEY_SURNAME);
                    int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                    int workExpIndex = cursor.getColumnIndex(DBHelper.KEY_WORKEXP);
                    int jobIndex = cursor.getColumnIndex(DBHelper.KEY_JOB);
                    int salaryIndex = cursor.getColumnIndex(DBHelper.KEY_SALARY);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    int phoneIndex = cursor.getColumnIndex(DBHelper.KEY_PHONE);
                    int cityIndex = cursor.getColumnIndex(DBHelper.KEY_CITY);
                    int aboutYouIndex = cursor.getColumnIndex(DBHelper.KEY_ABOUT);
                    int educationIndex = cursor.getColumnIndex(DBHelper.KEY_EDUCATION);
                    int educationPlaceIndex = cursor.getColumnIndex(DBHelper.KEY_EDUCATION_PLACE);


                     int employersListIndex = cursor.getColumnIndex(DBHelper.KEY_EMPLOYERS_LIST);


                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", city = " + cursor.getString(cityIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", surname = " + cursor.getString(surnameIndex) +
                                ", password = " + cursor.getString(passwordIndex) +
                                ", education = " + cursor.getString(educationIndex) +
                                ", education place = " + cursor.getString(educationPlaceIndex) +
                                ", work experience = " + cursor.getString(workExpIndex) +
                                ", about me  = " + cursor.getString(aboutYouIndex) +
                                ", job = " + cursor.getString(jobIndex) +
                                ", salary = " + cursor.getString(salaryIndex) +
                                ", phone  = " + cursor.getString(phoneIndex) +
                                ", email = " + cursor.getString(emailIndex)   +
                               ", employers list  = " + cursor.getString(employersListIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;

        }
        dbHelper.close();
    }


}