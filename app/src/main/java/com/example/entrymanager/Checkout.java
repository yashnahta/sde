package com.example.entrymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Checkout extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference dr;
    String vmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        database = FirebaseDatabase.getInstance();
        dr = database.getReference();
    }

    public void goCheckOut(View view) {
        EditText vphone=findViewById(R.id.vphone2);
        String visitorphone=vphone.getText().toString();


String message = "checked out";
       //String message="You have been checked out! Here are the visit details: \n"+"Name - "+name+"\nEmail - "+visitormail+"\nPhone - "+phone+"\nCheckIn Time - "+in+"\nTentative Check-out Time - "+out ;

    JavaMailAPI mailToHost = new JavaMailAPI(this, vmail, "Visit Details " , message);

        mailToHost.execute();
    }
}
