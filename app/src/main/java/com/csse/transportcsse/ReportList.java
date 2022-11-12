package com.csse.transportcsse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;

public class ReportList extends AppCompatActivity {

    Button button;
    ListView listView;
    List<Reports> user;
    DatabaseReference ref;

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_reports);

        button = (Button)findViewById(R.id.addDetails);
        listView = (ListView)findViewById(R.id.listview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportList.this, AddReport.class);
                startActivity(intent);
            }
        });

        user = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("managetrip");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()) {

                    Reports Reports = studentDatasnap.getValue(Reports.class);
                    user.add(Reports);
                }

                MyAdapter adapter = new MyAdapter(ReportList.this, R.layout.report_details, (ArrayList<Reports>) user);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    static class ViewHolder {

        TextView COL1;
        TextView COL2;
        TextView COL3;
        TextView COL4;
        TextView COL5;
        TextView COL6;

        Button deletebtn;
        Button button1;
    }

    class MyAdapter extends ArrayAdapter<Reports> {
        LayoutInflater inflater;
        Context myContext;
        List<Reports> user;
        public MyAdapter(Context context, int resource, ArrayList<Reports> objects) {
            super(context, resource, objects);
            myContext = context;
            user = objects;
            inflater = LayoutInflater.from(context);
            int y;
            String barcode;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.report_details, null);

                holder.COL1 = (TextView) view.findViewById(R.id.category);
                holder.COL2 = (TextView) view.findViewById(R.id.title);
                holder.COL3 = (TextView) view.findViewById(R.id.body);
                holder.COL4 = (TextView) view.findViewById(R.id.bustime);
                holder.COL5 = (TextView) view.findViewById(R.id.date);
                holder.COL6 = (TextView) view.findViewById(R.id.time);
                holder.button1 = (Button) view.findViewById(R.id.editbtn);
                holder.deletebtn = (Button) view.findViewById(R.id.deletebtn);

                view.setTag(holder);
            } else {

                holder = (ViewHolder) view.getTag();
            }

            //view data from db
            holder.COL1.setText("User ID:   " + user.get(position).getUserid());
            holder.COL2.setText("From:   " + user.get(position).getFrom());
            holder.COL3.setText("To:  " + user.get(position).getTo());
            holder.COL4.setText("Date: " + user.get(position).getDate());
            holder.COL5.setText("Time: " + user.get(position).getTime());
            holder.COL6.setText("Price: " + user.get(position).getPrice()+ "LKR");

            System.out.println(holder);

            //update a report
            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    View view1 = inflater.inflate(R.layout.update_report, null);
                    dialogBuilder.setView(view1);

                    final EditText editText1 = (EditText) view1.findViewById(R.id.product_name);
                    final EditText editText2 = (EditText) view1.findViewById(R.id.product_price);
                    final EditText editText3 = (EditText) view1.findViewById(R.id.product_brand);
                    final EditText editText4 = (EditText) view1.findViewById(R.id.product_category);

                    final Button buttonupdate = (Button) view1.findViewById(R.id.btnEdit1);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    final String idd = user.get(position).getId();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("managetrip");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String from = (String) snapshot.child("from").getValue();
                            String to = (String) snapshot.child("to").getValue();
                            String time = (String) snapshot.child("time").getValue();
                            String date = (String) snapshot.child("date").getValue();


                            editText1.setText(from);
                            editText2.setText(to);
                            editText3.setText(time);
                            editText4.setText(date);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    buttonupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String from = editText1.getText().toString();
                            String to = editText2.getText().toString();
                            String time = editText3.getText().toString();
                            String date = editText4.getText().toString();


                            if (from.isEmpty()) {
                                editText1.setError("Start location is required");
                            }else if (to.isEmpty()) {
                                editText2.setError("End location is required");
                            }else if (time.isEmpty()) {
                                editText3.setError("Time is required");
                            }else if (date.isEmpty()) {
                                editText4.setError("Date is required");
                            }else {

                                HashMap map = new HashMap();
                                map.put("from", from);
                                map.put("to", to);
                                map.put("time", time);
                                map.put("date", date);

                                reference.updateChildren(map);

                                Toast.makeText(ReportList.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ReportList.this, Home.class));
                                alertDialog.dismiss();
                            }
                        }
                    });
                }
            });

            return view;
        }

}

}
