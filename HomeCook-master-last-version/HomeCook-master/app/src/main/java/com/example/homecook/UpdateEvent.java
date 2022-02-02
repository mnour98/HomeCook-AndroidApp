package com.example.homecook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import model.Event;

public class UpdateEvent extends AppCompatActivity {

    EditText  edEventName, edDescription, edStartDate, edEndDate, edPrice, edCategory;
    ImageView imphoto;
    Button btnBrowse, btnUpload, btnReturn, btnUpdateEvent;
    String _userName,_id,_category,_description,_eventName,_startDate,_endDate,_photo,_price;
    DatabaseReference reference;

    Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);
        initialize();
    }

    private void initialize() {





        edEventName = findViewById(R.id.edEventName);
        edDescription = findViewById(R.id.edDescription);
        edStartDate = findViewById(R.id.edStartDate);
        edEndDate = findViewById(R.id.edEndDate);
        edPrice = findViewById(R.id.edPrice);
        edCategory = findViewById(R.id.edCategory);
        btnBrowse = findViewById(R.id.btnBrowse);
        btnUpload = findViewById(R.id.btnUpload);

        DisplayFeilds();
        btnUpdateEvent = findViewById(R.id.btnUpdateEvent);
        btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEvent();
            }
        });
        imphoto = findViewById(R.id.imPhoto);





    }

    private void updateEvent() {
        if (isEventNameIsChange()){
            Toast.makeText(this, "The Event Name Has Been Updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEventNameIsChange() {
        if(_eventName.equals(edEventName.getText().toString())){
            reference.child(_userName).child("eventName").setValue(edEventName.getText().toString());
            return true;
        }else{
            return false;
        }
    }

    private void DisplayFeilds() {

        Intent intent = getIntent();
        _id=intent.getStringExtra("id");
        _category=intent.getStringExtra("category");
        _description=intent.getStringExtra("description");
        _eventName=intent.getStringExtra("eventName");
        _startDate=intent.getStringExtra("startDate");
        _endDate=intent.getStringExtra("endDate");
        _photo=intent.getStringExtra("photo");
        _price=intent.getStringExtra("price");
        _userName=intent.getStringExtra("userName");



        edEventName.setText(_eventName);
        edDescription.setText(_description);
        edStartDate.setText(_startDate);
        edEndDate.setText(_endDate);
        edPrice.setText(_price);
        edCategory.setText(_category);



    }
}