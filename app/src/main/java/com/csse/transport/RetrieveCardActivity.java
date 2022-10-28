package com.csse.transport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveCardActivity extends AppCompatActivity {

    ListView myListView;
    List<CardPayments> cardPaymentsList;

    DatabaseReference paymentDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_card);

        myListView = findViewById(R.id.myListView);
        cardPaymentsList = new ArrayList<>();

        paymentDbRef = FirebaseDatabase.getInstance().getReference("CardPayment");

        paymentDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                cardPaymentsList.clear();

                for (DataSnapshot cardPaymentDatasnap : snapshot.getChildren()){
                    CardPayments cardPayments = cardPaymentDatasnap.getValue(CardPayments.class);
                    cardPaymentsList.add(cardPayments);
                }
                ListAdapter adapter = new ListAdapter(RetrieveCardActivity.this, cardPaymentsList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Making for Update the Details

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                CardPayments cardPayments = cardPaymentsList.get(position);


                return false;
            }
        });
    }


    }



