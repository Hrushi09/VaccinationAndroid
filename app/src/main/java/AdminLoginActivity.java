package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText etemail,etpass;
    Button btnadmin_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etemail = findViewById(R.id.et_email);
        etpass = findViewById(R.id.et_pass);
        btnadmin_login = findViewById(R.id.btnLogin);

        btnadmin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etemail.getText().toString().equals(""))
                {
                    Toast.makeText(AdminLoginActivity.this,"Enter Email",Toast.LENGTH_LONG).show();

                }
                else if(etpass.getText().toString().equals(""))
                {
                    Toast.makeText(AdminLoginActivity.this,"Enter Password",Toast.LENGTH_LONG).show();

                }
                else if(etemail.getText().toString().equals("admin")&&etpass.getText().toString().equals("admin"))
                {
                    Intent i = new Intent(AdminLoginActivity.this, AdminHomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(AdminLoginActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}