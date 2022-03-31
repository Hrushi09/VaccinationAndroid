package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    Button btnHospitals,btnapointmentlist,btneditapointment;
    TextView txtsignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnHospitals = findViewById(R.id.btnHospitals);
        btnHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, HospitalsListActivity.class);
                startActivity(i);
            }
        });
        btnapointmentlist = findViewById(R.id.btnapointmentlist);
        btnapointmentlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, DisplayMyApointmentsActivity.class);
                startActivity(i);
            }
        });
        btneditapointment = findViewById(R.id.btneditapointment);
        btneditapointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MyPendingApoinmentsActivity.class);
                startActivity(i);
            }
        });

        txtsignout=findViewById(R.id.txtsignout);
        txtsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}