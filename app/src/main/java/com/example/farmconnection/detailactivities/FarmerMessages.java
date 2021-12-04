package com.example.farmconnection.detailactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.farmconnection.R;
import com.example.farmconnection.ui.main.FarmerMessagesFragment;

public class FarmerMessages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_messages_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FarmerMessagesFragment.newInstance())
                    .commitNow();
        }
    }
}