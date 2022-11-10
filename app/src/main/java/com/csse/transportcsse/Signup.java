package com.csse.transportcsse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText editTextTextPersonName,editTextTextEmailAddress3,editTextPhone,editTextTextPassword2,editTextTextPersonName4;
    Button btn_2signin,btnsignup;

    User userob;

    FirebaseDatabase rootNode;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress3 = findViewById(R.id.editTextTextEmailAddress3);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);

        btnsignup = findViewById(R.id.btnsignup);

        userob = new User();

        //button dirrection

        btn_2signin = findViewById(R.id.btn_2signin);
        btn_2signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                startActivity(new Intent(Signup.this, Home.class));

                ClearControls();
            }
        });




    }

    public void Signup(View view) {

        rootNode = FirebaseDatabase.getInstance();
        db = rootNode.getReference("User");


        try {
            if(TextUtils.isEmpty(editTextTextPersonName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Name",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(editTextTextEmailAddress3.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Email",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(editTextPhone.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Mobile Number",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(editTextTextPassword2.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter a Password",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(editTextTextPersonName4.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter a Password",Toast.LENGTH_LONG).show();
            } else {
                userob.setName(editTextTextPersonName.getText().toString().trim());
                userob.setEmail(editTextTextEmailAddress3.getText().toString().trim());
                userob.setMobileNum(editTextPhone.getText().toString().trim());
                userob.setPassword(editTextTextPassword2.getText().toString().trim());
                userob.setNic(editTextTextPersonName4.getText().toString().trim());
                String Nic = editTextTextPersonName4.getText().toString().trim();

                db.child(Nic).setValue(userob);

                Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_LONG).show();

                startActivity(new Intent(Signup.this, MainActivity.class));

                ClearControls();

            }


        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_LONG).show();
        }
    }

    public void ClearControls() {
        editTextTextPersonName.setText("");
        editTextTextEmailAddress3.setText("");
        editTextPhone.setText("");
        editTextTextPassword2.setText("");
        editTextTextPersonName4.setText("");
        editTextTextPersonName4.setText("");


    }

}




