package com.csse.transportcsse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    Button button4,button8;

    TextView editTextTextPersonName2,editTextTextPersonName3,editTextTextPersonName5,editTextTextPersonName6,editTextTextPersonName7;

    DatabaseReference db;
    User userob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        db = FirebaseDatabase.getInstance().getReference("User");

        userob = new User();

        button4 = findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(UserProfile.this, Home.class));

            }
        });

        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7);


        //show
        
        showUserdata();

    }

    private void showUserdata() {

        //from sign in
        Intent intent = getIntent();
        String user_email = intent.getStringExtra("email");
        String user_name = intent.getStringExtra("name");
        String user_nic = intent.getStringExtra("nic");
        String user_password = intent.getStringExtra("password");
        String user_mobileNum = intent.getStringExtra("mobileNum");

        //from device
        if (user_nic == null) {
            SharedPreferences sharedPref = getSharedPreferences("myPref", MODE_PRIVATE);
            user_email = sharedPref.getString("email", "");
            user_name = sharedPref.getString("name", "");
            user_nic = sharedPref.getString("nic", "");
            user_password = sharedPref.getString("password", "");
            user_mobileNum = sharedPref.getString("mobileNum", "");
        }

        editTextTextPersonName2.setText(user_email);
        editTextTextPersonName3.setText(user_name);
        editTextTextPersonName5.setText(user_nic);
        editTextTextPersonName7.setText(user_password);
        editTextTextPersonName6.setText(user_mobileNum);

    }

    public void UpdateProfile(View view) {

       DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("User");
       upRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               if(dataSnapshot.hasChild(editTextTextPersonName5.getText().toString().trim())){

                   userob.setName(editTextTextPersonName2.getText().toString().trim());
                   userob.setEmail(editTextTextPersonName3.getText().toString().trim());
                   userob.setNic(editTextTextPersonName5.getText().toString().trim());
                   userob.setMobileNum(editTextTextPersonName6.getText().toString().trim());
                   userob.setPassword(editTextTextPersonName7.getText().toString().trim());
                   String Nic = editTextTextPersonName5.getText().toString().trim();

                   db = FirebaseDatabase.getInstance().getReference().child("User").child(Nic);
                   db.setValue(userob);


                   //Feedback to the user via a Toast..

                   Toast.makeText(getApplicationContext(), "Profile Updated Successfully",Toast.LENGTH_SHORT).show();


               }else
                   Toast.makeText(getApplicationContext(),"No Source to Update", Toast.LENGTH_SHORT).show();


           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }
    public void ClearControls() {
        editTextTextPersonName2.setText("");
        editTextTextPersonName3.setText("");
        editTextTextPersonName5.setText("");
        editTextTextPersonName6.setText("");
        editTextTextPersonName7.setText("");
        editTextTextPersonName5.setText("");

    }
}