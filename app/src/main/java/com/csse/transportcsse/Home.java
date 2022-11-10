package com.csse.transportcsse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    Button btnbmn,btnque, btnTT, btnHBM, btnBooking;
    TextView feedbackTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnbmn = findViewById(R.id.btnbmn);

        btnbmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Home.this, Signup.class));
            }
        });

        btnque = findViewById(R.id.btnque);

        btnque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Home.this, ScanQR.class));
            }
        });



//        btnTT = findViewById(R.id.btntt);
//        btnTT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(home.this, ManageBusTime.class));
//            }
//        });
//
//        btnHBM = findViewById(R.id.btnhbm);
//        btnHBM.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(home.this, HireActivity.class));
//            }
//        });
//
//        btnBooking = findViewById(R.id.btnDoneBookings);
//        btnBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(home.this, RetrieveCardActivity.class));
//            }
//        });
    }
}