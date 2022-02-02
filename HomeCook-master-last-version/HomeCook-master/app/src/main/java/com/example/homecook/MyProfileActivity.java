package com.example.homecook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfileActivity extends AppCompatActivity {

    TextView edname, edUsername, edFirstname, edLastname, edPassword, edAddress, edPostalcode, edAppartment, edEmail ;
    Button btnSaveChanges;
    String _USERNAME, _FIRSTNAME, _LASTNAME, _PASSWORD, _EMAIL, _ADDRESS, _POSTAL_CODE, _APPARTMENT;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        initialize();
    }

    private void initialize() {

        edname = findViewById(R.id.ed_name);
        edUsername = findViewById(R.id.edUsername);
        edFirstname = findViewById(R.id.edFirstName);
        edLastname = findViewById(R.id.edLastName);
        edPassword = findViewById(R.id.edPasswordChange);
        edEmail = findViewById(R.id.ed_user_email_address);
        edAddress = findViewById(R.id.edAddress);
        edPostalcode = findViewById(R.id.edPostalcode);
        edAppartment = findViewById(R.id.edAppartment);
        edUsername.setEnabled(false);
        reference = FirebaseDatabase.getInstance().getReference("user");
        btnSaveChanges = findViewById(R.id.btn_save_changes);
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        DisplayUsername();
    }

    private void update() {
        if (isNameChange()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        if (isPasswordChange()){
            Toast.makeText(this, "Password has been updated", Toast.LENGTH_SHORT).show();
        }
        if (isFirstNameChange()){
            Toast.makeText(this, "First name has been updated", Toast.LENGTH_SHORT).show();
        }
        if (isLastNameChange()){
            Toast.makeText(this, "Last name has been updated", Toast.LENGTH_SHORT).show();
        }
        if (isEmailChange()){
            Toast.makeText(this, "Email has been updated", Toast.LENGTH_SHORT).show();
        }
        if (isAddressChange()){
            Toast.makeText(this, "Address has been updated", Toast.LENGTH_SHORT).show();
        }
        if (isPostalcodeChange()){
            Toast.makeText(this, "Postal code has been updated", Toast.LENGTH_SHORT).show();
        }
        else if (isAppartmentChange()){
            Toast.makeText(this, "Apartment number has been updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAppartmentChange() {
        if (!_APPARTMENT.equals(edAppartment.getText().toString())){
            reference.child(_USERNAME).child("apartment").setValue(edAppartment.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isPostalcodeChange() {
        if (!_POSTAL_CODE.equals(edPostalcode.getText().toString())){
            reference.child(_USERNAME).child("postal_code").setValue(edPostalcode.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isAddressChange() {
        if (!_ADDRESS.equals(edAddress.getText().toString())){
            reference.child(_USERNAME).child("address").setValue(edAddress.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isEmailChange() {
        if (!_EMAIL.equals(edEmail.getText().toString())){
            reference.child(_USERNAME).child("email").setValue(edEmail.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isLastNameChange() {
        if (!_LASTNAME.equals(edLastname.getText().toString())){
            reference.child(_USERNAME).child("last_name").setValue(edLastname.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isFirstNameChange() {
        if (!_FIRSTNAME.equals(edFirstname.getText().toString())){
            reference.child(_USERNAME).child("first_name").setValue(edFirstname.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isPasswordChange() {
        if (!_PASSWORD.equals(edPassword.getText().toString())){
            reference.child(_USERNAME).child("password").setValue(edPassword.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isNameChange() {
        if (!_USERNAME.equals(edUsername.getText().toString())){
            reference.child(_USERNAME).child("userName").setValue(edUsername.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private void DisplayUsername() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        _USERNAME = intent.getStringExtra("userName");
        String firstname = intent.getStringExtra("first_name");
        _FIRSTNAME = intent.getStringExtra("first_name");
        String lastname = intent.getStringExtra("last_name");
        _LASTNAME = intent.getStringExtra("last_name");
        String password = intent.getStringExtra("password");
        _PASSWORD = intent.getStringExtra("password");
        String email = intent.getStringExtra("email");
        _EMAIL = intent.getStringExtra("email");
        String address = intent.getStringExtra("address");
        _ADDRESS = intent.getStringExtra("address");
        String postalcode = intent.getStringExtra("postal_code");
        _POSTAL_CODE = intent.getStringExtra("postal_code");
        String apartment = intent.getStringExtra("apartment");
        _APPARTMENT = intent.getStringExtra("apartment");

        edname.setText(firstname +" "+ lastname);
        edUsername.setText(userName);
        edFirstname.setText(firstname);
        edLastname.setText(lastname);
        edPassword.setText(password);
        edEmail.setText(email);
        edAddress.setText(address);
        edPostalcode.setText(postalcode);
        edAppartment.setText(apartment);
    }
}