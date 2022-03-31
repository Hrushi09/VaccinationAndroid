package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaccinationapp.Models.BookingPojo;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    int mYear, mMonth, mDay;
    String DAY, MONTH, YEAR;
    EditText fname,lname,age,gender,etDate;
    Spinner sp_gender,sp_time;
    ProgressDialog loadingBar;
    SharedPreferences sp;
    Button btnconfirm,btnCancel;
    String et_name, et_address, et_phone;
    DatabaseReference databaseReference;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        getSupportActionBar().setTitle("Book Appointment");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BookingPojo ibook = getIntent().getParcelableExtra("book");




        fname=findViewById(R.id.et_fname);
        lname=findViewById(R.id.et_lname);
        age=findViewById(R.id.et_age);
        sp_gender=findViewById(R.id.sp_gender);
        sp_time=findViewById(R.id.sp_time);



        et_name = getIntent().getStringExtra("name");
        et_address=getIntent().getStringExtra("address");
        et_phone= getIntent().getStringExtra("phone");


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Bookings");
//        String uid=  FirebaseAuth.getInstance().getCurrentUser().getUid();

        if(ibook!=null){{
           System.out.println(ibook.getEmail());
            fname.setText(ibook.getFirstname());
            lname.setText(ibook.getLastname());
            age.setText(ibook.getAge());
            etDate.setText(ibook.getBookingdate());
        }}


        sp = getSharedPreferences("AA", 0);
        username = sp.getString("uname", "-");

        btnconfirm=findViewById(R.id.btnconfirm);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (fname.getText().toString().isEmpty())
                {
                    Toast.makeText(BookAppointmentActivity.this, "write your Firstname...", Toast.LENGTH_SHORT).show();
                }

                else if (lname.getText().toString().isEmpty())
                {
                    Toast.makeText(BookAppointmentActivity.this, "write your Lastname...", Toast.LENGTH_SHORT).show();
                }
                else if(age.getText().toString().isEmpty())
                {
                    Toast.makeText(BookAppointmentActivity.this, "write your Age...", Toast.LENGTH_SHORT).show();
                }
                else if(etDate.getText().toString().isEmpty())
                {
                    Toast.makeText(BookAppointmentActivity.this, "select date...", Toast.LENGTH_SHORT).show();
                }


                else {
//                if(ibook!=null){
//
//
//
//                    HashMap<String, Object> hashMap = new HashMap<>();
//                    hashMap.put("firstname", fname.getText().toString());
//                    hashMap.put("lastname", lname.getText().toString());
//                    hashMap.put("age", age.getText().toString());
//                    hashMap.put("gender", sp_gender.getSelectedItem().toString());
//                    hashMap.put("bookingdate", etDate.getText().toString());
//                    hashMap.put("bookingtime", sp_time.getSelectedItem().toString());
//                    hashMap.put("name", et_name);
//                    hashMap.put("address", et_address);
//                    hashMap.put("phone", et_phone);
//
//                  //  hashMap.put("status", "Requested");
//                  //  hashMap.put("email", location.getText().toString());
//
//
//                    databaseReference.child(ibook.getEmail().toString()).updateChildren(hashMap).addOnSuccessListener(suc ->
//                    {
//                        Toast.makeText(BookAppointmentActivity.this, "Record is updated", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }).addOnFailureListener(er ->
//                    {
//                        Toast.makeText(BookAppointmentActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
//                    });
//                }else {
                    BookingPojo b = new BookingPojo(fname.getText().toString(), lname.getText().toString(), age.getText().toString(), sp_gender.getSelectedItem().toString(), etDate.getText().toString(), sp_time.getSelectedItem().toString(), et_name, et_address, et_phone, "Requested", username);
                    databaseReference.push().setValue(b).addOnSuccessListener(suc -> {
                        Toast.makeText(BookAppointmentActivity.this, "Booking Sent Successfully", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(BookAppointmentActivity.this, BookingConfirmActivity.class);
                        startActivity(i);
                        // finish();
                    }).addOnFailureListener(fail -> {
                        Toast.makeText(BookAppointmentActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    });
                    //  }
                }
            }
        });

        btnCancel=findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        etDate=findViewById(R.id.etDate);

        etDate.setFocusable(false);
        etDate.setClickable(true);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();

            }
        });


        loadingBar = new ProgressDialog(BookAppointmentActivity.this);
    }


    public void datepicker() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";

                        etDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }
}