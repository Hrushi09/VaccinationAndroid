package com.vaccinationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vaccinationapp.Models.BookingPojo;
import com.vaccinationapp.R;

import java.util.List;

public class AdminCancelAdapter extends BaseAdapter {
    List<BookingPojo> bookingRequestPojo;
    Context context;
    String Uname;

    public AdminCancelAdapter(Context context, List<BookingPojo> mytrips) {
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
        View obj2 = obj1.inflate(R.layout.child_adminstatus, null);

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



        return obj2;
    }

}
