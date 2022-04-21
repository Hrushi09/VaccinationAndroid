package com.vaccinationapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaccinationapp.AdminHomeActivity;
import com.vaccinationapp.Models.BookingPojo;
import com.vaccinationapp.R;

import java.util.HashMap;
import java.util.List;

public class AdminRequestsAdapter extends BaseAdapter {
    List<BookingPojo> bookingRequestPojo;
    Context context;
    String Uname;

    public AdminRequestsAdapter(Context context, List<BookingPojo> mytrips) {
        this.context=context;
        this.bookingRequestPojo=mytrips;
       // this.clickListener = clickListener;
        //this.Uname=uname;
    }

    ClickListener clickListener;

    public interface ClickListener {
        public void onClick(int position);

        public void cancel(int position);

    }

    @Override
    public int getCount() {
        return bookingRequestPojo.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater obj1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2 = obj1.inflate(R.layout.child_requests, null);

        TextView tvname=(TextView)obj2.findViewById(R.id.tvname);
        tvname.setText("Name : "+bookingRequestPojo.get(position).getFirstname());

        TextView tvhosname=(TextView)obj2.findViewById(R.id.tvhosname);
        tvhosname.setText("Hospital Name : "+bookingRequestPojo.get(position).getName());

        TextView tvaddress=(TextView)obj2.findViewById(R.id.tvaddress);
        tvaddress.setText("Address : "+bookingRequestPojo.get(position).getAddress());

        TextView tvdate=(TextView)obj2.findViewById(R.id.tvdate);
        tvdate.setText("Booking Date: "+bookingRequestPojo.get(position).getBookingdate());

        TextView tvtime=(TextView)obj2.findViewById(R.id.tvtime);
        tvtime.setText("Booking Time : "+bookingRequestPojo.get(position).getBookingtime());


        Button btncancel=(Button) obj2.findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Bookings");

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", "Cancelled");

                ref.child(bookingRequestPojo.get(position).getKey().toString()).updateChildren(hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(context, "Cancelled updated", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(context, AdminHomeActivity.class);

                    context.startActivity(i);

                }).addOnFailureListener(er ->
                {
                    Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }
        });

        Button btnconfirm=(Button) obj2.findViewById(R.id.btnconfirm);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Bookings");

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", "Confirmed");

                ref.child(bookingRequestPojo.get(position).getKey().toString()).updateChildren(hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(context, "Confirmed Successfully", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(context, AdminHomeActivity.class);

                    context.startActivity(i);

                }).addOnFailureListener(er ->
                {
                    Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }
        });








        return obj2;
    }

}
