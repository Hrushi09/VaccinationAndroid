package com.vaccinationapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vaccinationapp.HospitalDetailsActivity;
import com.vaccinationapp.Models.Hospital;
import com.vaccinationapp.R;

import java.util.List;

public class HospitalListAdapter extends BaseAdapter {
    List<Hospital> viewHospitalPojo;
    Context context;

    public HospitalListAdapter(Context context, List<Hospital> hospitals) {
        this.context=context;
        this.viewHospitalPojo=hospitals;
    }

    @Override
    public int getCount() {
        return viewHospitalPojo.size();
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
        View obj2 = obj1.inflate(R.layout.child_hospital, null);

        TextView tvname=(TextView)obj2.findViewById(R.id.tvname);
        tvname.setText(viewHospitalPojo.get(position).getName());


        ImageView imglocation=(ImageView) obj2.findViewById(R.id.imglocation);
        imglocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HospitalDetailsActivity.class);
                intent.putExtra("name",viewHospitalPojo.get(position).getName());
                intent.putExtra("address",viewHospitalPojo.get(position).getAddress());
                intent.putExtra("phone",viewHospitalPojo.get(position).getPhone());

                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        return obj2;
    }
}
