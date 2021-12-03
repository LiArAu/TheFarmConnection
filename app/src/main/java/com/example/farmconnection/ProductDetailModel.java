package com.example.farmconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
                Intent i = new Intent(ProductDetailModel.this, SelectNewPlants_database_version.class);
                startActivity(i);
            }
        });
    }
}