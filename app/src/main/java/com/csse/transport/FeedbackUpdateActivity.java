package com.csse.transport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackUpdateActivity extends AppCompatActivity {

    EditText updFeedEmail, updFeedDesc;
    Button btnUpdFeedback;
    Feedback feedback;

    DatabaseReference DbFeedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_update);

        updFeedEmail = findViewById(R.id.tex_UpdatedEmail);
        updFeedDesc = findViewById(R.id.tex_UpdatedDescription);

        btnUpdFeedback= findViewById(R.id.btn_UpdateFeedback);
        btnUpdFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference updFeedRef = FirebaseDatabase.getInstance().getReference().child("Feedbacks");
                updFeedRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("email")){
                            try {
                                feedback.setEmail(updFeedEmail.getText().toString().trim());
                                feedback.setFeedback(updFeedDesc.getText().toString().trim());

                                DbFeedRef = FirebaseDatabase.getInstance().getReference().child("Feedbacks");
                                DbFeedRef.setValue(feedback);
                                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Oops couldn't update the data",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}