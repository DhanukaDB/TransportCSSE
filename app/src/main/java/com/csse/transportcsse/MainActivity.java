package com.csse.transportcsse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button btnsignin;
    User userob;

    EditText editTextTextEmailAddress2,editTextTextPassword;

    DatabaseReference db;

    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userob = new User();

        button = findViewById(R.id.button);
        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Signup.class)));


    }


    public void Signin(View view) {





        String enteredUsername = editTextTextEmailAddress2.getText().toString().trim();
        String enteredPassword = editTextTextPassword.getText().toString().trim();

        db = FirebaseDatabase.getInstance().getReference().child("User");

        Query checkUser = db.orderByChild("nic").equalTo(enteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){



                    String passwordFromDB = dataSnapshot.child(enteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(enteredPassword)){

                        String emailFromDB = dataSnapshot.child(enteredUsername).child("email").getValue(String.class);
                        String mobileNumFromDB = dataSnapshot.child(enteredUsername).child("mobileNum").getValue(String.class);
                        String nameFromDB = dataSnapshot.child(enteredUsername).child("name").getValue(String.class);
                        String nicFromDB = dataSnapshot.child(enteredUsername).child("nic").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(),Signup.class);
                        Intent intent1 = new Intent(getApplicationContext(),Signup.class);


                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("nic",nicFromDB);
                        intent.putExtra("mobileNum",mobileNumFromDB);
                        intent.putExtra("password",passwordFromDB);


                        intent1.putExtra("nic",nicFromDB);


                        saveToDevice(nameFromDB, emailFromDB, nicFromDB, mobileNumFromDB, passwordFromDB);

                        Toast.makeText(getApplicationContext()," Successfully Logged in",Toast.LENGTH_LONG).show();
                        startActivity(intent);

                        ClearControls();
                    }else {

                        Toast.makeText(getApplicationContext(),"Password Error",Toast.LENGTH_LONG).show();
                        ClearControls();
                    }
                }else {

                    Toast.makeText(getApplicationContext(),"Username Error",Toast.LENGTH_LONG).show();
                    ClearControls();
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        editTextTextEmailAddress2.setText("");
        editTextTextPassword.setText("");

    }
    private void saveToDevice(String name, String email, String nic, String mobile, String password) {
        SharedPreferences sharedPref = getSharedPreferences("myPref", MODE_PRIVATE);
        sharedPref.edit().putString("name", name).apply();
        sharedPref.edit().putString("email", email).apply();
        sharedPref.edit().putString("nic", nic).apply();
        sharedPref.edit().putString("mobileNum", mobile).apply();
        sharedPref.edit().putString("password", password).apply();
    }

    public void ClearControls() {
        editTextTextEmailAddress2.setText("");
        editTextTextPassword.setText("");

    }


}