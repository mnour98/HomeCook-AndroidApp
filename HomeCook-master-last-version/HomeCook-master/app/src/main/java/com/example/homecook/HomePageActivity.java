package com.example.homecook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import model.Event;
import model.EventAdapter;

public class HomePageActivity extends AppCompatActivity implements ChildEventListener, View.OnClickListener, ValueEventListener, AdapterView.OnItemClickListener {

    ListView lvEvents;
    ArrayList<Event> listOfEvents;
    //ArrayAdapter<Event> eventAdapter;
    EventAdapter eventAdapter;
    DatabaseReference homeCookDB;

    TextView tvFirst_Name,tvEvent;
    ImageView imHomeCookProfile, imgMyProfile;

    String eventName, userName, first_name, last_name, password, address, email, postal_code, appartment ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initialize();
    }

    private void initialize(){
        imHomeCookProfile = findViewById(R.id.imProfile);
        imHomeCookProfile.setOnClickListener(this);
        imgMyProfile = findViewById(R.id.imgAvatar);
        imgMyProfile.setOnClickListener(this);
        lvEvents = findViewById(R.id.lvEvents);
        tvFirst_Name = findViewById(R.id.tvFirstName);
        DisplayFirstName();
        homeCookDB = FirebaseDatabase.getInstance()
                .getReference("event");
        lvEvents.setOnItemClickListener(this);
        listOfEvents = new ArrayList<Event>();
        homeCookDB.addChildEventListener(this);
        homeCookDB.addValueEventListener(this);

        //eventAdapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1,listOfEvents);
        eventAdapter = new EventAdapter(this,listOfEvents);
        lvEvents.setAdapter(eventAdapter);

        Intent intent = getIntent();

        first_name = intent.getStringExtra("first_name");
        last_name = intent.getStringExtra("last_name");
        password = intent.getStringExtra("password");
        email = intent.getStringExtra("email");
        address = intent.getStringExtra("address");
        postal_code = intent.getStringExtra("postal_code");
        appartment = intent.getStringExtra("apartment");





    }

    public void DisplayFirstName(){

        Intent intent = getIntent();
        String first_name = intent.getStringExtra("first_name");
        tvFirst_Name.setText("Welcome "+first_name);
        userName = intent.getStringExtra("userName");








    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        if(snapshot.exists())
        {
            String id = snapshot.child("id").getValue().toString();
            String category = snapshot.child("category").getValue().toString();
            String description = snapshot.child("description").getValue().toString();
            String eventName = snapshot.child("eventName").getValue().toString();
            String startDate = snapshot.child("startDate").getValue().toString();
            String endDate = snapshot.child("endDate").getValue().toString();
            String photo = snapshot.child("photo").getValue().toString();
            double price = Double.parseDouble(snapshot.child("price").getValue().toString()) ;
            String userName = snapshot.child("userName").getValue().toString();
            listOfEvents.add(new Event(id,category,description,eventName,startDate,endDate,photo,price,userName));
            eventAdapter.notifyDataSetChanged();



        }



    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        try {
            if (snapshot.exists()){
                String eventId = snapshot.child("id").getValue().toString();
                String category = snapshot.child("category").getValue().toString();
                String description = snapshot.child("description").getValue().toString();
                String endDate = snapshot.child("endDate").getValue().toString();
                String eventName = snapshot.child("eventName").getValue().toString();
                String userName = snapshot.child("userName").getValue().toString();
            }

        } catch (Exception e){
            Toast.makeText(this, "There is no event for this user", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.imgAvatar:
                userProfileActivity();

                break;
            case R.id.imProfile:
                homeCookProfile();
                break;
            case R.id.tvEvent:
                updateEventAct();
                break;
    }
}

    private void userProfileActivity() {

        Intent intent = new Intent(this, MyProfileActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);
        intent.putExtra("first_name", first_name);
        intent.putExtra("last_name", last_name);
        intent.putExtra("email", email);
        intent.putExtra("address", address);
        intent.putExtra("postal_code", postal_code);
        intent.putExtra("apartment", appartment);
        startActivity(intent);


    }
    private void updateEventAct() {
        Intent intent = new Intent(this, UpdateEvent.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }
    private void homeCookProfile() {

        Intent intent = new Intent(this, ManageEventActivity.class);

        intent.putExtra("userName", userName);

        startActivity(intent);


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, Payment.class);
        intent.putExtra("id", listOfEvents.get(i).getId());
        intent.putExtra("category", listOfEvents.get(i).getCategory());
        intent.putExtra("eventName", listOfEvents.get(i).getEventName());



        startActivity(intent);
    }
}