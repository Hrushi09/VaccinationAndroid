package com.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class HospitalDetailsActivity extends AppCompatActivity {

    EditText et_name, et_address, et_phone;
    Button btnselect;
    ProgressDialog loadingBar;
    String currentDate,currentTime;
    SharedPreferences sp;
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        getSupportActionBar().setTitle("Hospital Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        webview = (WebView) findViewById(R.id.webView1);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://maps.google.com/maps");


        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_phone = findViewById(R.id.et_phone);
        btnselect=findViewById(R.id.btnselect);


        et_name.setText( getIntent().getStringExtra("name"));
        et_address.setText(getIntent().getStringExtra("address"));
        et_phone.setText( getIntent().getStringExtra("phone"));
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HospitalDetailsActivity.this, BookAppointmentActivity.class);
                intent.putExtra("name",et_name.getText().toString());
                intent.putExtra("address",et_address.getText().toString());
                intent.putExtra("phone",et_phone.getText().toString());
                startActivity(intent);
            }
        });
    }
}