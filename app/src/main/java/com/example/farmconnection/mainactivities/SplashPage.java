package com.example.farmconnection.mainactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farmconnection.R;

public class SplashPage extends AppCompatActivity {

    private static int splash_screen = 5000;

    //Variables
    Animation top, bottom;
    ImageView image0;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        top = AnimationUtils.loadAnimation(this, R.anim.pic_anim);
        bottom = AnimationUtils.loadAnimation(this, R.anim.sentence_anim);
        image0 = findViewById(R.id.image0);
        slogan = findViewById(R.id.slogan);
        image0.setAnimation(top);
        slogan.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashPage.this, Dashboard.class);
                startActivity(i);
                finish(); // won't go back to this page.
            }
        }, splash_screen);



    }
}