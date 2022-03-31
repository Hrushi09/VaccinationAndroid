package com.vaccinationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText et_name, et_email, et_pass;
    TextView tvsignin;
    Button btnRegister;
    ProgressDialog loadingBar;
    String currentDate,currentTime;
    SharedPreferences sp;
    String name,email,phone,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadingBar = new ProgressDialog(SignUpActivity.this);

        tvsignin=findViewById(R.id.tvsignin);
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateData();
            }
        });
    }

    private void ValidateData()
    {
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        pass = et_pass.getText().toString();



        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            StoreUserInformation();
        }
    }

    private void StoreUserInformation() {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!(dataSnapshot.child("Users").child(name).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();

                    userdataMap.put("name", name);
                    userdataMap.put("email", email);
                    userdataMap.put("pass", pass);

                    RootRef.child("Users").child(name).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SignUpActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(SignUpActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "This " + email + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(SignUpActivity.this, "Please try again using another Email.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUpActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}