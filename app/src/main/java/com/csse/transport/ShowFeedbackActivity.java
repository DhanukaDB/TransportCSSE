package com.csse.transport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class ShowFeedbackActivity extends AppCompatActivity {

    ListView listFeedbackView;
    List<Feedback> feedbackList;

    DatabaseReference feedbackDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feedback);

        listFeedbackView = findViewById(R.id.listFeedbackView);
        feedbackList = new ArrayList<>();

        feedbackDbRef = FirebaseDatabase.getInstance().getReference("Feedbacks");

        feedbackDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feedbackList.clear();

                for (DataSnapshot feedbackDatasnap: snapshot.getChildren()){

                    Feedback feedback = feedbackDatasnap.getValue(Feedback.class);
                    feedbackList.add(feedback);
                }

                FeedbackAdapter feedbackAdapter = new FeedbackAdapter(ShowFeedbackActivity.this, feedbackList);
                listFeedbackView.setAdapter(feedbackAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        listFeedbackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowFeedbackActivity.this, FeedbackUpdateActivity.class);
                startActivity(intent);

            }
        });

    }
}