package com.example.farmconnection.detailactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farmconnection.R;
import com.example.farmconnection.mainactivities.SelectNewPlants;

public class ProductDetailModel extends AppCompatActivity {
    TextView title, provider, price, description;
    ImageView image;
    Button buy, goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        title = findViewById(R.id.detailtitle);
        provider = findViewById(R.id.detailprovider);
        price = findViewById(R.id.detailprice);
        description = findViewById(R.id.detaildescription);
        image = findViewById(R.id.detailimage);
        buy = findViewById(R.id.buttonbuy);
        goback = findViewById(R.id.buttongoback);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        provider.setText("Provider: "+intent.getStringExtra("provider"));
        price.setText("Price: $"+intent.getIntExtra("price",-1));
        description.setText(intent.getStringExtra("content"));
        image.setImageBitmap(BitmapFactory.decodeByteArray(intent.getByteArrayExtra("image")
                ,0,intent.getByteArrayExtra("image").length));

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectNewPlants.class);
                startActivity(i);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Payment.class);
                startActivity(i);
            }
        });
    }
}