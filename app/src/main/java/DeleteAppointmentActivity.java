package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteAppointmentActivity extends AppCompatActivity {

    Button btnreturn,btnbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_appointment);


        btnreturn=(Button)findViewById(R.id.btnreturn);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DeleteAppointmentActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        btnbook=(Button)findViewById(R.id.btnbook);
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DeleteAppointmentActivity.this, BookAppointmentActivity.class);
                startActivity(i);
            }
        });

    }
}