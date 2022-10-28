package com.csse.transport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    Button btnbmn,button5, btnTT, btnHBM, btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnbmn = findViewById(R.id.btnbmn);

        btnbmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(home.this, Bookings.class));
            }
        });

        button5 = findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(home.this, UserProfile.class));
            }
        });

        btnTT = findViewById(R.id.btntt);
        btnTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, ManageBusTime.class));
            }
        });

        btnHBM = findViewById(R.id.btnhbm);
        btnHBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, HireActivity.class));
            }
        });

        btnBooking = findViewById(R.id.btnDoneBookings);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, RetrieveCardActivity.class));
            }
        });
    }
}