package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText et_USERNAME,et_PWD;
    Button btnLogin;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setTitle("Forgot Password");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_USERNAME = findViewById(R.id.et_USERNAME);
        et_PWD = findViewById(R.id.et_PWD);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Users");

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_USERNAME.getText().toString().isEmpty())
                {
                    Toast.makeText(ForgotPasswordActivity.this, "write your Email...", Toast.LENGTH_SHORT).show();
                }

                else if (et_PWD.getText().toString().isEmpty())
                {
                    Toast.makeText(ForgotPasswordActivity.this, "write your New Password...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final DatabaseReference RootRef;
                    RootRef = FirebaseDatabase.getInstance().getReference("Users");
                  //  Query query=RootRef.orderByChild("name").equalTo(et_USERNAME.getText().toString());


                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("pass", et_PWD.getText().toString());


                    RootRef.child(et_USERNAME.getText().toString()).updateChildren(hashMap).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(ForgotPasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(intent);
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(ForgotPasswordActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });



    }
}