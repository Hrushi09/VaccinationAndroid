package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vaccinationapp.Adapters.HospitalListAdapter;
import com.vaccinationapp.Models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalsListActivity extends AppCompatActivity {

    List<Hospital> hospital;
    ListView list_view;
    private String parentDbName = "Add_hospitals";
    String username;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    DatabaseReference dbMyTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_list);
        getSupportActionBar().setTitle("Hospitals List");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);

        getHospitals();
    }

    private void getHospitals() {
        hospital = new ArrayList<>();
        progressDialog = new ProgressDialog(HospitalsListActivity.this);
        progressDialog.setTitle("Please Wait data is being Loaded");
        progressDialog.show();
        Query query = FirebaseDatabase.getInstance().getReference("Add_hospitals");
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            progressDialog.dismiss();
            hospital.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Hospital hospitals = snapshot.getValue(Hospital.class);

                        hospital.add(hospitals);

                }
                if (hospital.size() > 0) {
                    list_view.setAdapter(new HospitalListAdapter( HospitalsListActivity.this,hospital));
                }
            } else {
                Toast.makeText(HospitalsListActivity.this, "No Hospitals Found", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            progressDialog.dismiss();

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}