package com.example.homecook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgetPassword extends AppCompatActivity  implements View.OnClickListener, ValueEventListener, ChildEventListener {
    EditText edUsername1;
    Button btnResetPass,btnRet1;
    DatabaseReference userDatabase,userChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initialize();
    }

    private void initialize() {

        edUsername1 = findViewById(R.id.edUsername1);
        btnResetPass = findViewById(R.id.btnResetPass);
        btnRet1 = findViewById(R.id.btnRet1);
        btnResetPass.setOnClickListener(this);
        btnRet1.setOnClickListener(this);
        userDatabase = FirebaseDatabase.getInstance().getReference("user");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnResetPass:
                changePass();
                break;


            case R.id.btnRet1:
                backTo();
                break;
        }


    }

    private void backTo() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void changePass() {
        if (edUsername1.getText().toString().equals("")){
            Toast.makeText(this, "Please entre your email or username", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "We send a link to your email", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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
    public void onDataChange(@NonNull DataSnapshot snapshot) {




    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}