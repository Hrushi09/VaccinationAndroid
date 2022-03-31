package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminHomeActivity extends AppCompatActivity {

    CardView cd_hospitals,cd_pendings,cd_confirmed,cd_cancelled;
    TextView txtsignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().setTitle("Admin Home");

        cd_hospitals = findViewById(R.id.cd_hospitals);
        cd_pendings = findViewById(R.id.cd_pendings);
        cd_confirmed = findViewById(R.id.cd_confirmed);
        cd_cancelled = findViewById(R.id.cd_cancelled);

        txtsignout=findViewById(R.id.txtsignout);
        txtsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        cd_hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomeActivity.this, AddHospitalActivity.class);
                startActivity(i);
            }
        });
        cd_pendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomeActivity.this, AdminViewAllApointmentsActivity.class);
                startActivity(i);
            }
        });
        cd_confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomeActivity.this, AdminConfirmApointmentsActivity.class);
                startActivity(i);
            }
        });
        cd_cancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomeActivity.this, AdminCancelApointmentsActivity.class);
                startActivity(i);
            }
        });
    }
}