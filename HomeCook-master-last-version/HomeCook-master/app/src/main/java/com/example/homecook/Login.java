package com.example.homecook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener,ValueEventListener  {

    EditText edUserName,etPassword;
     Button btnReg,btnLog;
     TextView tvForgotPassword;
    DatabaseReference userDatabase,userChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize() {

        edUserName = findViewById(R.id.edUserName);
        etPassword = findViewById(R.id.etPassword);
        btnReg =  findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);
        btnLog = findViewById(R.id.btnLog);
        btnLog.setOnClickListener(this);

        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //openact();
        int id = view.getId();
        switch(id) {
            case R.id.btnReg:
                signIn();
                break;

            case R.id.tvForgotPassword:
                forgetpass();
                break;
            case R.id.btnLog:
                loginTo();
                break;

    }


    }

    private void loginTo() {

        String userName = edUserName.getText().toString();
        String password = etPassword.getText().toString();
        userChild = FirebaseDatabase.getInstance().getReference()
                .child("user").child(userName);

        userChild.addValueEventListener(this);



    }

    private void forgetpass() {
        edUserName = findViewById(R.id.edUserName);
        etPassword = findViewById(R.id.etPassword);
        Intent intent = new Intent(this, ForgetPassword.class);
        startActivity(intent);

    }

    private void signIn() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        try {


            if (snapshot.exists()) {

                String userName = snapshot.child("userName").getValue().toString();
                String firstName = snapshot.child("first_name").getValue().toString();
                String last_name = snapshot.child("last_name").getValue().toString();
                String password = snapshot.child("password").getValue().toString();
                String email = snapshot.child("email").getValue().toString();
                String address = snapshot.child("address").getValue().toString();
                String postalcode = snapshot.child("postal_code").getValue().toString();
                String appartment = snapshot.child("apartment").getValue().toString();
                ArrayList<String> eventsList = new ArrayList<>();

                if (edUserName.getText().toString().equals(userName)
                        && etPassword.getText().toString().equals(password)) {


                    Intent intent = new Intent(this, HomePageActivity.class);

                    intent.putExtra("userName", userName);
                    intent.putExtra("password", password);
                    intent.putExtra("first_name", firstName);
                    intent.putExtra("last_name", last_name);
                    intent.putExtra("email", email);
                    intent.putExtra("address", address);
                    intent.putExtra("postal_code", postalcode);
                    intent.putExtra("apartment", appartment);
                    startActivity(intent);
                }


                else if ( !etPassword.getText().toString().equals(password)){
                    Toast.makeText(this, "Please, entre the correct password", Toast.LENGTH_SHORT).show();


                }


            }

            else {

                Toast.makeText(this, "The username " + edUserName.getText().toString() + " does not exist", Toast.LENGTH_SHORT).show();
                edUserName.setText(null);
                etPassword.setText(null);

                edUserName.requestFocus();


            }
        }
        catch (Exception e){
            Toast.makeText(this, "Please, entre your information", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}