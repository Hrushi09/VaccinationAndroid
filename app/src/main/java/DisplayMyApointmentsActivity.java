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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vaccinationapp.Adapters.MyBookingsAdapter;
import com.vaccinationapp.Models.BookingPojo;

import java.util.ArrayList;
import java.util.List;

public class DisplayMyApointmentsActivity extends AppCompatActivity {

    List<BookingPojo> viewBookingPojo;
    ListView list_view;
    private String parentDbName = "Bookings";
    String username;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_my_apointments);
        getSupportActionBar().setTitle("My Bookings");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sp = getSharedPreferences("AA", 0);
        username = sp.getString("uname", "-");

        list_view=(ListView)findViewById(R.id.list_view);

        getMyBookingsDetails();

        Toast.makeText(DisplayMyApointmentsActivity.this, username, Toast.LENGTH_SHORT).show();

    }
    private void getMyBookingsDetails() {
        viewBookingPojo = new ArrayList<>();
        progressDialog = new ProgressDialog(DisplayMyApointmentsActivity.this);
        progressDialog.setTitle("Please Wait data is being Loaded");
        progressDialog.show();
        Query query = FirebaseDatabase.getInstance().getReference("Bookings").orderByChild("email").equalTo(username);
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            progressDialog.dismiss();
            viewBookingPojo.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BookingPojo viewrequests = snapshot.getValue(BookingPojo.class);

                    viewrequests.setKey(snapshot.getKey());



                        viewBookingPojo.add(viewrequests);


                }
                if (viewBookingPojo.size() > 0) {
                    list_view.setAdapter(new MyBookingsAdapter( DisplayMyApointmentsActivity.this,viewBookingPojo));
                }
            } else {
                Toast.makeText(DisplayMyApointmentsActivity.this, "No Bookings Found", Toast.LENGTH_SHORT).show();
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