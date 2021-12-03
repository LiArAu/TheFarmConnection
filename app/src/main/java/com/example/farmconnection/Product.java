package com.example.farmconnection;
import android.graphics.Bitmap;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {
    // define column names;
    @PrimaryKey(autoGenerate = true)
    public int prod_id;
    public String title;
    public String content;
    public String provider;
    public int price;
    public byte[] image;
    public int image_id;

    // constructer
    public Product(String title, String content, String provider, Integer price, byte[] image, Integer image_id){
        this.title = title;
        this.content = content;
        this.provider = provider;
        this.price = price;
        this.image = image;
        this.image_id = image_id;
    }

    public int getId() { return prod_id; }

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public String getProvider() { return provider; }

    public int getPrice() { return price; }

    public byte[] getImage() { return image; }

    public int getImage_id() { return image_id;}

    public void setId(int id) { this.prod_id = prod_id; }

}
