package com.example.homecook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

import model.Event;
import model.EventAdapter;

public class ManageEventActivity extends AppCompatActivity implements ChildEventListener, View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    ListView lvEvents;
    ArrayList<String> events= new ArrayList<String>();
    ArrayList<Event> listOfEvents;
    EventAdapter eventAdapter;
    DatabaseReference eventDatabase;
    String userName;
    FloatingActionButton floatingActionButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_event);
        initialize();
    }

    public void initialize(){

        Intent intent = getIntent();
         userName = intent.getStringExtra("userName");
        floatingActionButton = findViewById(R.id.card_btn);
        lvEvents = findViewById(R.id.lvEvents);
        lvEvents.setOnItemClickListener(this);



            eventDatabase= FirebaseDatabase.getInstance()
                    .getReference().child("event");
            eventDatabase.addChildEventListener(this);;

        listOfEvents = new ArrayList<Event>();
        eventAdapter = new EventAdapter(this, listOfEvents);
        lvEvents.setAdapter(eventAdapter);


    }
    @Override

    public void onClick(View view) {
        //openact();
        int id = view.getId();
        switch (id) {
            case R.id.card_btn:
                startAddEvent();
                break;
        }
    }
    public void startAddEvent()
    {
        Intent intent = new Intent(this, AddEventActivity.class);

        intent.putExtra("userName", userName);

        startActivity(intent);

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {



            if(snapshot.child("userName").getValue().toString().equals(userName)) {

                String id = snapshot.child("id").getValue().toString();
                String category = snapshot.child("category").getValue().toString();
                String description = snapshot.child("description").getValue().toString();
                String eventName = snapshot.child("eventName").getValue().toString();
                String startDate = snapshot.child("startDate").getValue().toString();
                String endDate = snapshot.child("endDate").getValue().toString();
                String photo = snapshot.child("photo").getValue().toString();
                double price = Double.parseDouble(snapshot.child("price").getValue().toString());
                String userName = snapshot.child("userName").getValue().toString();
                Event event  = new Event(id, category, description, eventName, startDate, endDate, photo, price, userName);

                listOfEvents.add(event);


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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this,UpdateEvent.class);
        intent.putExtra("id",listOfEvents.get(position).getId());
        intent.putExtra("category",listOfEvents.get(position).getCategory());
        intent.putExtra("description",listOfEvents.get(position).getDescription());
        intent.putExtra("eventName",listOfEvents.get(position).getEventName());
        intent.putExtra("startDate",listOfEvents.get(position).getStartDate());
        intent.putExtra("endDate",listOfEvents.get(position).getEndDate());
        intent.putExtra("photo",listOfEvents.get(position).getPhoto());
        intent.putExtra("price",Double.toString(listOfEvents.get(position).getPrice()));
        intent.putExtra("userName",listOfEvents.get(position).getEventName());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}