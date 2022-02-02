package com.example.homecook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity implements View.OnClickListener {

    String eventid;
    EditText edCardNumber, edCBC, edQuantity;
    TextView tvEv1;
    Button btnBuy,btnRet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initialize();
    }

    private void initialize() {
        tvEv1 = findViewById(R.id.tvEv1);
        edCardNumber = findViewById(R.id.edCardNumber);
        edCBC = findViewById(R.id.edCBC);
        edQuantity = findViewById(R.id.edQuantity);
        btnBuy = findViewById(R.id.btnBuy);
        btnRet = findViewById(R.id.btnRet);
        btnBuy.setOnClickListener(this);
        btnRet.setOnClickListener(this);
        Intent intent = getIntent();
        eventid = intent.getStringExtra("eventName");
        tvEv1.setText(eventid);

    }





    private void goesbackto() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    private void goTo() {


        if (edCardNumber.length() == 0) {
            edCardNumber.setError("Entre your Card Number");
        } else if (edCBC.length() == 0) {
            edCBC.setError("Entre your CBC number");
        } else if (edQuantity.length() == 0) {
            edQuantity.setError("Entre quantity");
        } else {


            Toast.makeText(this, "Thank you. you buy your food", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnBuy:
                goTo();


            case R.id.btnRet:
                goesbackto();
                break;
        }

    }
}