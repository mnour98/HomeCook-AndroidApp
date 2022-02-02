package com.example.homecook;

import static java.util.UUID.randomUUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.UUID;

import model.Event;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener, OnSuccessListener, OnFailureListener, OnCompleteListener, ValueEventListener {


    EditText  edEventName, edDescription, edStartDate, edEndDate, edPrice, edCategory;
    ImageView imphoto;
    Button btnBrowse, btnUpload, btnReturn, btnAddEvent;
    DatabaseReference eventDatabase,userDatabase;
    FirebaseStorage storage;
    StorageReference storageReference,sRef;
    int IMAGE_REQUEST = 71;
    Uri filePath;
    String photoURl;
    ArrayList<String> events = new ArrayList<String>();


    ProgressDialog progressDialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData() != null){
            // receive the image file
            filePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imphoto.setImageBitmap(bitmap);
                Log.d("ADV_FIREBASE", "The image is displayed successfully");
            }catch(Exception e)
            {
                Log.d("ADV_FIREBASE", e.getMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        initialize();
    }

    private void initialize() {

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        edEventName = findViewById(R.id.edEventName);
        edDescription = findViewById(R.id.edDescription);
        edStartDate = findViewById(R.id.edStartDate);
        edEndDate = findViewById(R.id.edEndDate);
        edPrice = findViewById(R.id.edPrice);
        edCategory = findViewById(R.id.edCategory);
        btnBrowse = findViewById(R.id.btnBrowse);
        btnUpload = findViewById(R.id.btnUpload);
        //btnReturn = findViewById(R.id.btnReturn);
        btnAddEvent = findViewById(R.id.btnAddEvent);
        imphoto = findViewById(R.id.imPhoto);
        
        btnBrowse.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        //btnReturn.setOnClickListener(this);
        btnAddEvent.setOnClickListener(this);
        userDatabase = FirebaseDatabase.getInstance()
                .getReference().child("user").child(userName);
        userDatabase.addValueEventListener(this);

        eventDatabase = FirebaseDatabase
                .getInstance()
                .getReference("event");


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnBrowse:selectphoto(); break;
            case R.id.btnUpload: uploadphoto(); break;
            //case R.id.btnReturn: goback(); break;
            case R.id.btnAddEvent: addEvent(); break;
        }

    }

    private void uploadphoto() {
        if (filePath != null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading a photo in progress...");
            progressDialog.show();
            sRef = storageReference.child("eventImages/"+ randomUUID());
            sRef.putFile(filePath).addOnSuccessListener(this);
            sRef.putFile(filePath).addOnFailureListener(this);



        }
    }

    private void selectphoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_REQUEST );
    }

//    private void goback() {
//
//        Intent intent = new Intent(this, HomePageActivity.class);
//
//        startActivity(intent);
//
//    }

    private void addEvent() {
        try{
            userDatabase.addValueEventListener(this);
            Intent intent = getIntent();
            String userName = intent.getStringExtra("userName");
            String eventid =  randomUUID().toString();
            String eventName = edEventName.getText().toString();
            String description = edDescription.getText().toString();
            String startDate = edStartDate.getText().toString();
            String endDate = edEndDate.getText().toString();
            double price = Double.valueOf(edPrice.getText().toString());
            String category = edCategory.getText().toString();
            String photo = photoURl;


            events.add(eventid);


            Event event1 = new Event(eventid,category,description,eventName,startDate,endDate,photo,price,userName);
            eventDatabase.child(eventid).setValue(event1);
            userDatabase.child("listOfEvents").setValue(events);

            Toast.makeText(this, "This Event with id" + eventid + "is added successfully", Toast.LENGTH_SHORT).show();
            Clr();
        }
        
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void Clr() {

        edEventName.setText(null);
        edDescription.setText(null);
        edStartDate.setText(null);
        edEndDate.setText(null);
        edPrice.setText(null);
        edCategory.setText(null);

    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onSuccess(Object o) {
        Toast.makeText(this, "This photo has been  uploaded successfully ", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        //get the photo url
        sRef.getDownloadUrl().addOnCompleteListener(this);

    }

    @Override
    public void onComplete(@NonNull Task task) {
        Log.d("ADV_FIREBASE", "The url of the photo is "+ task.getResult().toString());
        photoURl = task.getResult().toString();
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()){

            events = (ArrayList)snapshot.child("listOfEvents").getValue();

        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}