

package com.csse.transportcsse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddReport extends AppCompatActivity {
    Spinner from,to;
    EditText userid;
    EditText price;
    EditText date,time;
    Button button2,button3;
    Spinner spinner;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreport);

        from = (Spinner) findViewById(R.id.spinner);
        to = (Spinner) findViewById(R.id.spinner2);
        userid = (EditText) findViewById(R.id.editTextTextPersonName5);
        price = (EditText) findViewById(R.id.editTextTextPersonName6);
        date = (EditText) findViewById(R.id.editTextTextPersonName8);
        time = (EditText) findViewById(R.id.editTextTextPersonName9);
        button2 = (Button) findViewById(R.id.buttonFeedback);
        button3 = (Button) findViewById(R.id.button3);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.from_area, android.R.layout.simple_spinner_item);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter3);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.to_area, android.R.layout.simple_spinner_item);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    insertData();
                    clearAll();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
    //insert data to database
    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("from", from.getSelectedItem().toString());
        map.put("to", to.getSelectedItem().toString());

        map.put("userid", userid.getText().toString());
        map.put("price", price.getText().toString());
        map.put("date", date.getText().toString());
        map.put("time", time.getText().toString());


        //Integer.parseInt(qty.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("managetrip").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddReport.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddReport.this, ReportList.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddReport.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private boolean CheckAllFields() {

        if (date.length() == 0) {
            date.setError("This field is required");
            return false;
        }
        if (userid.length() == 0) {
            userid.setError("This field is required");
            return false;
        }

        return true;
    }
    private void clearAll() {

        userid.setText("");
        date.setText("");
        time.setText("");

    }

}
