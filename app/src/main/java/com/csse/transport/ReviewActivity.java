package com.csse.transport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewActivity extends AppCompatActivity {

    EditText Rev_Rate, Rev_Email, Rev_Other;
    Button Submit_Rev;

    DatabaseReference dbReviewRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Rev_Rate = findViewById(R.id.tex_RevRate);
        Rev_Email = findViewById(R.id.tex_RevEmail);
        Rev_Other = findViewById(R.id.tex_RevOther);

        Submit_Rev = findViewById(R.id.btn_SubmitRev);

        dbReviewRef = FirebaseDatabase.getInstance().getReference().child("Reviews");

        Submit_Rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertReviewData();
            }
        });

    }

    private void insertReviewData(){
        String rate = Rev_Rate.getText().toString();
        String email = Rev_Email.getText().toString();
        String other = Rev_Other.getText().toString();

        Review review = new Review(rate, email, other);
        dbReviewRef.push().setValue(review);
        Toast.makeText(ReviewActivity.this ,"Review inserted", Toast.LENGTH_SHORT).show();
    }
}