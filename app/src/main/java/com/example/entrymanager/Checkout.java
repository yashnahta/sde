package com.example.entrymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Checkout extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference dr;


    String intime;
    String p;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        database = FirebaseDatabase.getInstance();
        dr = database.getReference();

        EditText vname = findViewById(R.id.vname2);



        vname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                EditText vphone = findViewById(R.id.vphone2);

                p=vphone.getText().toString();
                dr=database.getReference().child("Visitor").child(p).child("Mail");
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue(String.class);
                        intime=value;
                        Toast.makeText(getApplicationContext(),p,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        // Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });



                return false;
            }
        });

    }

    public void goCheckOut(View view) {
        //EditText vphone=findViewById(R.id.vphone2);
        //String visitorphone=vphone.getText().toString();

String vmail = "17ucs010@lnmiit.ac.in";
String message = "checked out";
       //String message="You have been checked out! Here are the visit details: \n"+"Name - "+name+"\nEmail - "+visitormail+"\nPhone - "+phone+"\nCheckIn Time - "+in+"\nTentative Check-out Time - "+out ;

    JavaMailAPI mailToHost = new JavaMailAPI(this, vmail, "Visit Details " , intime);

        mailToHost.execute();
    }
}
