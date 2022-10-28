package com.csse.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingSuccess extends AppCompatActivity {

    Button btncancel,button2,button8;
    EditText editTextTextPersonName7,editTextTextPersonName10,editTextTextPersonName11,editTextTextPersonName13,editTextTextPersonName14,editTextTextPersonName9,editTextTextPersonName12;

    DatabaseReference db;
    Bookdetails bkdob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        db = FirebaseDatabase.getInstance().getReference("Bookings");

        bkdob = new Bookdetails();

        button8 = findViewById(R.id.button8);

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(BookingSuccess.this, home.class));

            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingSuccess.this, CardActivity.class));
            }
        });

        editTextTextPersonName10 = findViewById(R.id.editTextTextPersonName10);
        editTextTextPersonName11 = findViewById(R.id.editTextTextPersonName11);
        editTextTextPersonName13 = findViewById(R.id.editTextTextPersonName13);
        editTextTextPersonName14 = findViewById(R.id.editTextTextPersonName14);
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7);
        editTextTextPersonName9 = findViewById(R.id.editTextTextPersonName9);
        editTextTextPersonName12 = findViewById(R.id.editTextTextPersonName12);

        showUserdata();

    }
    private void showUserdata() {

        Intent intent = getIntent();
        String user_from = intent.getStringExtra("from");
        String user_to = intent.getStringExtra("to");
        String user_date = intent.getStringExtra("date");
        String user_time = intent.getStringExtra("time");
        String user_category = intent.getStringExtra("category");
        String user_email = intent.getStringExtra("email");
        String user_qty = intent.getStringExtra("qty");


        editTextTextPersonName14.setText(user_from);
        editTextTextPersonName9.setText(user_to);
        editTextTextPersonName10.setText(user_date);
        editTextTextPersonName7.setText(user_time);
        editTextTextPersonName12.setText(user_category);
        editTextTextPersonName13.setText(user_email);
        editTextTextPersonName11.setText(user_qty);

    }

    public void cancelbk(View view) {


        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Bookings");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(editTextTextPersonName10.getText().toString().trim()));
                db.removeValue();


                Toast.makeText(getApplicationContext(), "Booking Cancelled", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),UserProfile.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();



            }
        });



    }
}