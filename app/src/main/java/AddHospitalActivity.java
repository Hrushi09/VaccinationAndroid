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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class AddHospitalActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText et_name, et_address, et_phone;
    Button btnadd;
    ProgressDialog loadingBar;
    String currentDate,currentTime;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        getSupportActionBar().setTitle("Add Hospital");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_phone = findViewById(R.id.et_phone);
        btnadd = findViewById(R.id.btnadd);

        loadingBar = new ProgressDialog(AddHospitalActivity.this);

       // FirebaseDatabase db = FirebaseDatabase.getInstance();
       // databaseReference = db.getReference(Hospital.class.getSimpleName());
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et_name.getText().toString();
                String address = et_address.getText().toString();
                String phone = et_phone.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(AddHospitalActivity.this, "Name should not be empty...", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(AddHospitalActivity.this, "Address should not be empty...", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(AddHospitalActivity.this, "Phone should not be empty...", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    loadingBar.setTitle("Adding Trip Details");
                    loadingBar.setMessage("Please wait, while we are Adding your Hospital Details.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    AddHospitalDetailsToDatabase(name, address, phone);
                }


            }
        });
    }

    private void AddHospitalDetailsToDatabase(String name, String address, String phone) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String, Object> userdataMap = new HashMap<>();
                userdataMap.put("name", name);
                userdataMap.put("address", address);
                userdataMap.put("phone", phone);
                userdataMap.put("hospitalAddDate_Time",currentDate+currentTime);

                RootRef.child("Add_hospitals").child(currentDate+currentTime).updateChildren(userdataMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(AddHospitalActivity.this, "Hospital Details Added Succussfully.", Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();

                                    Intent intent = new Intent(AddHospitalActivity.this, AdminHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    loadingBar.dismiss();
                                    Toast.makeText(AddHospitalActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}