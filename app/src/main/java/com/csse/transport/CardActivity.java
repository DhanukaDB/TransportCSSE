package com.csse.transport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardActivity extends AppCompatActivity {

    EditText payName, payAddress, payNumber;
    EditText payCard, payExpiry, payCcv;
    Button btnInsert;

    DatabaseReference paymentDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        payName = findViewById(R.id.tex_Name);
        payAddress = findViewById(R.id.tex_Address);
        payNumber = findViewById(R.id.tex_Num);

        payCard = findViewById(R.id.tex_CardNum);
        payExpiry = findViewById(R.id.tex_ExpiryDate);
        payCcv = findViewById(R.id.tex_CCV);

        btnInsert = findViewById(R.id.btn_Conf);

        paymentDbRef = FirebaseDatabase.getInstance().getReference().child("CardPayment");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPaymentData();
                Intent intent = new Intent(CardActivity.this, PaymentSuccessActivity.class);
                startActivity(intent);
            }
        });


    }
    private void insertPaymentData(){

        String name = payName.getText().toString();
        String address = payAddress.getText().toString();
        String number = payNumber.getText().toString();
        String card = payCard.getText().toString();
        String expiry = payExpiry.getText().toString();
        String ccv = payCcv.getText().toString();

        CardPayments cardPayments = new CardPayments(name,address,number, card,expiry,ccv);
        paymentDbRef.push().setValue(cardPayments);
        Toast.makeText(CardActivity.this, "Payment details recorded for additional purposes...", Toast.LENGTH_SHORT).show();
    }
}