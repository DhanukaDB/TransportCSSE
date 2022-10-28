package com.csse.transport;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HiredActivity extends AppCompatActivity {

    private RecyclerView recyclerViewHire;

    private FirebaseDatabase hireDb = FirebaseDatabase.getInstance();
    private DatabaseReference root = hireDb.getReference().child("Hiring");

    private HireAdapter hireAdapter;
    private ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hired);

        recyclerViewHire = findViewById(R.id.recyclerView_Hire);
        recyclerViewHire.setHasFixedSize(true);
        recyclerViewHire.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        hireAdapter = new HireAdapter(this, list);

        recyclerViewHire.setAdapter(hireAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Model modelHire = dataSnapshot.getValue(Model.class);
                    list.add(modelHire);
                }
                hireAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}