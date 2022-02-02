package com.example.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import model.User;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener , ValueEventListener {


    EditText edFirstName,edLastName,edUserName,edPassword,edEmail,edDateOfBirth,edAddress,edPostalCode,edApt;
    Button btnRegister,btnReturn;
    DatabaseReference userDatabase,userChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialize();
    }

    private void initialize() {

        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        edEmail = findViewById(R.id.edEmail);
        edDateOfBirth = findViewById(R.id.edDateOfBirth);
        edAddress = findViewById(R.id.edAddress);
        edPostalCode = findViewById(R.id.edPostalCode);
        edApt = findViewById(R.id.edApt);

        btnRegister = findViewById(R.id.btnRegister);
        btnReturn = findViewById(R.id.btnReturn);
        btnRegister.setOnClickListener(this);
        btnReturn.setOnClickListener(this);

        userDatabase = FirebaseDatabase.getInstance().getReference("user");


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.btnRegister:
                register();
                break;

            case R.id.btnReturn:
                goBack();
                break;


        }

    }

    private void goBack() {
        String userName = edUserName.getText().toString();
        String password = edPassword.getText().toString();
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);

        startActivity(intent);


    }


    private void register() {

        try {





            String userName = edUserName.getText().toString();

            String first_name = edFirstName.getText().toString();
            String last_name = edLastName.getText().toString();
            String email = edEmail.getText().toString();
            String postal_code = edPostalCode.getText().toString();
            String address = edAddress.getText().toString();
            String password = edPassword.getText().toString();
            int apartment = Integer.valueOf(edApt.getText().toString());
            String date_of_birth = edDateOfBirth.getText().toString();


            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
            }

/*
The source of the previous code from this link:

" https://www.geeksforgeeks.org/implement-email-validator-in-android/ "


*/



            if (edFirstName.length() == 0) {
                edFirstName.setError("Entre your First Name");
            } else if (edLastName.length() == 0) {
                edLastName.setError("Entre your Last Name");
            } else if (edUserName.length() == 0) {
                edUserName.setError("Entre your Username");
            } else if (edEmail.length() == 0) {
                edEmail.setError("Entre your Email");
            }

              else if (!email.isEmpty() && ! Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();

                }

            else if (edPostalCode.length()==0){
                edPostalCode.setError("Entre your postal code");
            }

            else if (edAddress.length()==0){
                edAddress.setError("Entre your Address");
            }
            else if (edPassword.length()==0){
                edPassword.setError("Entre your password");
            }

            else if (edApt.length()==0){
                edApt.setError("Entre your apt number");
            }

            else if (edDateOfBirth.length()==0){

                edDateOfBirth.setError("Entre your birthday");
            }



            else {



                userChild = FirebaseDatabase.getInstance().getReference()
                        .child("user").child(userName);
                userChild.addValueEventListener(this);


            }




        }
        catch (Exception e){
            Toast.makeText(this,"Please, entre your information",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {


            if (snapshot.exists()){

                String userName = snapshot.child("userName").getValue().toString();

                Toast.makeText(this,"The username is already Exist",Toast.LENGTH_SHORT).show();

            }
            else {


                String userName = edUserName.getText().toString();


                String first_name = edFirstName.getText().toString();
                String last_name = edLastName.getText().toString();
                String email = edEmail.getText().toString();
                String postal_code = edPostalCode.getText().toString();
                String address = edAddress.getText().toString();
                String password = edPassword.getText().toString();
                int apartment = Integer.valueOf(edApt.getText().toString());
                String date_of_birth = edDateOfBirth.getText().toString();
                String isHomeCook = "no";
                ArrayList<String> events = new ArrayList<>();





                User user = new User(first_name, last_name, userName, email, postal_code, address, password, date_of_birth,isHomeCook,apartment,events);
                userDatabase.child(edUserName.getText().toString()).setValue(user);
                Toast.makeText(this, "You are registered now. Thank you!", Toast.LENGTH_SHORT).show();





            }

        }




    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}