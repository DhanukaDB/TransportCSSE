package com.csse.transport;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageBusTime extends AppCompatActivity {

    Button button;
    ListView listView;
    List<TimaTableDetails> user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_bus_time);

        button = (Button)findViewById(R.id.addDetails);
        listView = (ListView)findViewById(R.id.listview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageBusTime.this, AddTimeDetails.class);
                startActivity(intent);
            }
        });

        user = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("TimeDetails");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()) {

                    TimaTableDetails timaTableDetails = studentDatasnap.getValue(TimaTableDetails.class);
                    user.add(timaTableDetails);
                }

                MyAdapter adapter = new MyAdapter(ManageBusTime.this, R.layout.custom_timetable_details, (ArrayList<TimaTableDetails>) user);
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
        Button button1;
        Button button2;
    }

    class MyAdapter extends ArrayAdapter<TimaTableDetails> {
        LayoutInflater inflater;
        Context myContext;
        List<TimaTableDetails> user;


        public MyAdapter(Context context, int resource, ArrayList<TimaTableDetails> objects) {
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
                view = inflater.inflate(R.layout.custom_timetable_details, null);

                holder.COL1 = (TextView) view.findViewById(R.id.busNo);
                holder.COL2 = (TextView) view.findViewById(R.id.busfrom);
                holder.COL3 = (TextView) view.findViewById(R.id.busto);
                holder.COL4 = (TextView) view.findViewById(R.id.bustime);
                holder.button1 = (Button) view.findViewById(R.id.deledit);
                holder.button2 = (Button) view.findViewById(R.id.deldelete);


                view.setTag(holder);
            } else {

                holder = (ViewHolder) view.getTag();
            }

            holder.COL1.setText("Bus No:- "+user.get(position).getBusNo());
            holder.COL2.setText("From:- "+user.get(position).getStartfrom());
            holder.COL3.setText("To:- "+user.get(position).getGoTo());
            holder.COL4.setText("Time:- "+user.get(position).getTime());
            System.out.println(holder);

            holder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("Do you want to delete this item?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    final String idd = user.get(position).getId();
                                    FirebaseDatabase.getInstance().getReference("TimeDetails").child(idd).removeValue();
                                    //remove function not written
                                    Toast.makeText(myContext, "Item deleted successfully", Toast.LENGTH_SHORT).show();

                                }
                            })

                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }
            });

            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    View view1 = inflater.inflate(R.layout.custom_update_time_details, null);
                    dialogBuilder.setView(view1);

                    final EditText editText1 = (EditText) view1.findViewById(R.id.TTbusNo);
                    final EditText editText2 = (EditText) view1.findViewById(R.id.TTstartLocatiojn);
                    final EditText editText3 = (EditText) view1.findViewById(R.id.TTEndLocation);
                    final EditText editText4 = (EditText) view1.findViewById(R.id.TTtime);
                    final EditText editText5 = (EditText) view1.findViewById(R.id.TTdriver);
                    final Button buttonupdate = (Button) view1.findViewById(R.id.update);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    final String idd = user.get(position).getId();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TimeDetails").child(idd);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String bnumber = (String) snapshot.child("busNo").getValue();
                            String start = (String) snapshot.child("startfrom").getValue();
                            String end = (String) snapshot.child("goTo").getValue();
                            String time = (String) snapshot.child("time").getValue();
                            String driver = (String) snapshot.child("driverName").getValue();

                            editText1.setText(bnumber);
                            editText2.setText(start);
                            editText3.setText(end);
                            editText4.setText(time);
                            editText5.setText(driver);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    buttonupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String busno = editText1.getText().toString();
                            String start = editText2.getText().toString();
                            String to = editText3.getText().toString();
                            String time = editText4.getText().toString();
                            String driver = editText5.getText().toString();

                           if (busno.isEmpty()) {
                                editText1.setError("Bus no is required");
                            }else if (start.isEmpty()) {
                                editText2.setError("Start location is required");
                            }else if (to.isEmpty()) {
                                editText3.setError("End location is required");
                            }else if (time.isEmpty()) {
                                editText4.setError("Time is required");
                            }else if (driver.isEmpty()) {
                                editText5.setError("Driver name is required");
                            }else {

                                HashMap map = new HashMap();
                                map.put("busNo", busno);
                                map.put("startfrom", start);
                                map.put("goTo", to);
                               map.put("time", time);
                               map.put("driverName", driver);
                                reference.updateChildren(map);

                                Toast.makeText(ManageBusTime.this, "Updated successfully", Toast.LENGTH_SHORT).show();

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
