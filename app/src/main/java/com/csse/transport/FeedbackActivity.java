package com.csse.transport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    EditText tex_Feed , tex_Email;
    Button btnFeed;

    DatabaseReference DbFeedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        tex_Feed = findViewById(R.id.tex_Feed);
        tex_Email = findViewById(R.id.tex_Email);

        btnFeed = findViewById(R.id.btn_Feed);

        DbFeedRef = FirebaseDatabase.getInstance().getReference().child("Feedbacks");

        btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertFeedbackData();
            }
        });
    }

    private void insertFeedbackData(){
        String email = tex_Email.getText().toString();
        String feedback = tex_Feed.getText().toString();

        Feedback feedback1 =  new Feedback(email, feedback);
        DbFeedRef.push().setValue(feedback1);
        Toast.makeText(FeedbackActivity.this, "Submitted the feedback successfully" , Toast.LENGTH_SHORT).show();
    }
}