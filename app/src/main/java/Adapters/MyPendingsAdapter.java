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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vaccinationapp.DeleteAppointmentActivity;
import com.vaccinationapp.EditAppointmentActivity;
import com.vaccinationapp.Models.BookingPojo;
import com.vaccinationapp.R;

import java.util.List;

public class MyPendingsAdapter extends BaseAdapter {
    List<BookingPojo> bookingRequestPojo;
    Context context;
    String Uname;
    DatabaseReference databaseReference;

    public MyPendingsAdapter(Context context, List<BookingPojo> mytrips) {
        this.context=context;
        this.bookingRequestPojo=mytrips;
        //this.Uname=uname;
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
        View obj2 = obj1.inflate(R.layout.child_modify, null);

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

        TextView tvstatus=(TextView)obj2.findViewById(R.id.tvstatus);
        tvstatus.setText("Status : "+bookingRequestPojo.get(position).getStatus());


        Button btndelete=(Button) obj2.findViewById(R.id.btndelete);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                databaseReference = db.getReference("Bookings");
                databaseReference.child(bookingRequestPojo.get(position).getKey()).removeValue().addOnSuccessListener(suc->{
                    Toast.makeText(context, "Deleted Successfully",Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                    Intent i =new Intent(context, DeleteAppointmentActivity.class);

                    context.startActivity(i);

                }).addOnFailureListener(er->{
                    Toast.makeText(context, er.getMessage(),Toast.LENGTH_LONG).show();

                });
                Toast.makeText(context, bookingRequestPojo.get(position).getKey(),Toast.LENGTH_LONG).show();


            }
        });

        Button btnmodify=(Button) obj2.findViewById(R.id.btnmodify);
        btnmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(context, EditAppointmentActivity.class);
                i.putExtra("name",bookingRequestPojo.get(position).getName());
                i.putExtra("address",bookingRequestPojo.get(position).getAddress());
                i.putExtra("bookingdate",bookingRequestPojo.get(position).getBookingdate());
                i.putExtra("bookingtime",bookingRequestPojo.get(position).getBookingtime());
                i.putExtra("key",bookingRequestPojo.get(position).getKey());
                context.startActivity(i);

            }
        });





        return obj2;
    }

    private void deleteTrip (final String datetime){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Bookings").orderByChild("tripData_Time").equalTo(datetime);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}