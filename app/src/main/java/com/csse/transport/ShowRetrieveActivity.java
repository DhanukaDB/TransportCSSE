package com.csse.transport;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

class ShowRetrieveActivity extends AppCompatActivity {

    ListView listReviewView;
    List<Review> reviewList;

    DatabaseReference reviewDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_retrieve);

        listReviewView = findViewById(R.id.listReviewView);
        reviewList = new ArrayList<>();

        reviewDbRef = FirebaseDatabase.getInstance().getReference("Reviews");

        reviewDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reviewList.clear();

                for (DataSnapshot reviewDataSnap : snapshot.getChildren()){

                    Review review = reviewDataSnap.getValue(Review.class);
                    reviewList.add(review);
                }
                ReviewAdapter adapter = new ReviewAdapter(ShowRetrieveActivity.this, reviewList);
                listReviewView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}