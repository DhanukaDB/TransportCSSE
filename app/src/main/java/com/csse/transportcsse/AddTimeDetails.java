package com.csse.transportcsse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTimeDetails extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    Button button;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time_details);

        ref = FirebaseDatabase.getInstance().getReference("TimeDetails");

        editText1 = (EditText)findViewById(R.id.Tid);
        editText2 = (EditText)findViewById(R.id.TbusNo);
        editText3 = (EditText)findViewById(R.id.TstartLocatiojn);
        editText4 = (EditText)findViewById(R.id.TEndLocation);
        editText5 = (EditText)findViewById(R.id.Ttime);
        editText6 = (EditText)findViewById(R.id.Tdriver);
        button = (Button)findViewById(R.id.addtime);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editText1.getText().toString();
                String busno = editText2.getText().toString();
                String start = editText3.getText().toString();
                String to = editText4.getText().toString();
                String time = editText5.getText().toString();
                String driver = editText6.getText().toString();

                if (code.isEmpty()) {
                    editText1.setError("Id is required");
                }else if (busno.isEmpty()) {
                    editText2.setError("Bus no is required");
                }else if (start.isEmpty()) {
                    editText3.setError("Start location is required");
                }else if (to.isEmpty()) {
                    editText4.setError("End location is required");
                }else if (time.isEmpty()) {
                    editText5.setError("Time is required");
                }else if (driver.isEmpty()) {
                    editText6.setError("Driver name is required");
                }else {

                    TimaTableDetails timaTableDetails = new TimaTableDetails(code,busno,start,to,time,driver);
                    ref.child(code).setValue(timaTableDetails);

                    Toast.makeText(AddTimeDetails.this, "Successfully added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddTimeDetails.this, ManageBusTime.class);
                    startActivity(intent);
                }
            }
        });
    }
}