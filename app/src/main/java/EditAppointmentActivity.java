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
import java.util.HashMap;

public class EditAppointmentActivity extends AppCompatActivity {
    int mYear, mMonth, mDay;
    String DAY, MONTH, YEAR;
    EditText et_name,et_address,etDate;
    Spinner sp_time;
    Button btnconfirm,btnCancel;
    String name, address, key;
    DatabaseReference databaseReference;
    SharedPreferences sp;
    ProgressDialog loadingBar;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);
        getSupportActionBar().setTitle("ReSchedule Appointment");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BookingPojo ibook = getIntent().getParcelableExtra("book");




        et_name=findViewById(R.id.et_name);
        et_address=findViewById(R.id.et_address);

        sp_time=findViewById(R.id.sp_time);


        et_name.setText(getIntent().getStringExtra("name"));
        et_address.setText(getIntent().getStringExtra("address"));
        name = getIntent().getStringExtra("name");
        address=getIntent().getStringExtra("address");
        key=getIntent().getStringExtra("key");


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Bookings");
        sp = getSharedPreferences("AA", 0);
        username = sp.getString("uname", "-");

        btnconfirm=findViewById(R.id.btnconfirm);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (et_name.getText().toString().isEmpty())
                {
                    Toast.makeText(EditAppointmentActivity.this, "write your Firstname...", Toast.LENGTH_SHORT).show();
                }

                else if (et_address.getText().toString().isEmpty())
                {
                    Toast.makeText(EditAppointmentActivity.this, "write your Lastname...", Toast.LENGTH_SHORT).show();
                }

                else if(etDate.getText().toString().isEmpty())
                {
                    Toast.makeText(EditAppointmentActivity.this, "select date...", Toast.LENGTH_SHORT).show();
                }


                else {

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("bookingdate", etDate.getText().toString());
                    hashMap.put("bookingtime", sp_time.getSelectedItem().toString());

                    databaseReference.child(key).updateChildren(hashMap).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(EditAppointmentActivity.this, "Appointment is updated", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(EditAppointmentActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
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


        loadingBar = new ProgressDialog(EditAppointmentActivity.this);
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