package com.csse.transport;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class HireActivity extends AppCompatActivity {

    //For Calendar and Clock
    Button btnGetDate;
    TextView tvDate;

    TextView tvCurrentTime;
    Button btnPickTime;

    int day;
    int month;
    int year;

    int currentHr;
    int currentMin;

    Calendar calendar;

    //For Database connection and add

    private TextView tex_Date, tex_Clock;
    private EditText tex_Destination, tex_DaysWant, tex_PeopleNum;
    private Button btn_Hire;

    private FirebaseDatabase hireDb = FirebaseDatabase.getInstance();
    private DatabaseReference root = hireDb.getReference().child("Hiring");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        //the Calendar and Clock
        btnGetDate = findViewById(R.id.btn_Calendar);
        tvDate = findViewById(R.id.tex_Date);

        tvCurrentTime= findViewById(R.id.tex_Clock);
        btnPickTime= findViewById(R.id.btn_Clock);

        //to add details and save it to database
        tex_Date = findViewById(R.id.tex_Date);
        tex_Clock = findViewById(R.id.tex_Clock);
        tex_Destination = findViewById(R.id.tex_Destin);
        tex_DaysWant = findViewById(R.id.tex_DaysWant);
        tex_PeopleNum = findViewById(R.id.tex_PeopleNum);
        btn_Hire = findViewById(R.id.btn_HirePro);

        btnPickTime.setOnClickListener(view->{
            calendar = Calendar.getInstance();

            currentHr = calendar.get(Calendar.HOUR);
            currentMin = calendar.get(Calendar.MINUTE);

            TimePickerDialog dialog = new TimePickerDialog(HireActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    tvCurrentTime.setText(hourOfDay+":"+ minute);
                }
            },currentHr,currentMin,false);
            dialog.show();
        });

        btnGetDate.setOnClickListener(view ->{
            Calendar calendar = Calendar.getInstance();

            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month+1;
                    String date = dayOfMonth +"-" + month + "-" + year;
                    tvDate.setText(date);
                }
            },year,month,day);
            datePickerDialog.show();
        });

        btn_Hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Date = tex_Date.getText().toString();
                String Time = tex_Clock.getText().toString();
                String Destination = tex_Destination.getText().toString();
                String Days = tex_DaysWant.getText().toString();
                String Person_Count = tex_PeopleNum.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("Date", Date);
                userMap.put("Time", Time);
                userMap.put("Destination", Destination);
                userMap.put("Days", Days);
                userMap.put("Person_Count", Person_Count);

                root.push().setValue(userMap);
                Toast.makeText(HireActivity.this, "Details were recorded successfully.", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(HireActivity.this, home.class));
            }
        });
    }
}