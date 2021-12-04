package com.example.farmconnection.mainactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farmconnection.detailactivities.FarmerMessages;
import com.example.farmconnection.R;
import com.example.farmconnection.detailactivities.ViewImage;

public class CheckCurrentPlants extends AppCompatActivity {

    Button message, pic, appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_current_plants);

        message = findViewById(R.id.buttonmessage);
        pic = findViewById(R.id.buttonpicture);
        appointment = findViewById(R.id.buttonappointment);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckCurrentPlants.this, FarmerMessages.class);
                startActivity(i);
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckCurrentPlants.this, ViewImage.class);
                startActivity(i);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckCurrentPlants.this, MakeAppointment.class);
                startActivity(i);
            }
        });
    }


}