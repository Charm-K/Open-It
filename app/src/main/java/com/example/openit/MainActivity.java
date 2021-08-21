package com.example.openit;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView_main;
    private ImageView imageView_ch1_1;
    private ImageView imageView_ch1_2;
    private Button button_start;
    public static boolean start = true; //완성 후 false로 바꾸기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(start==false){
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            start = true;
        }

        setContentView(R.layout.activity_main);

        imageView_main = (ImageView)findViewById(R.id.imageView_main);
        imageView_ch1_1 = (ImageView)findViewById(R.id.imageView_ch1_1);
        imageView_ch1_2 = (ImageView)findViewById(R.id.imageView_ch1_2);
        button_start = (Button)findViewById(R.id.button_satrt);

        imageView_ch1_1.setX(imageView_ch1_1.getX() + 1800);
        imageView_ch1_2.setX(imageView_ch1_2.getX() + 1800 * 2);
    }

    public void onClick(View view) {
        button_start.setVisibility(View.INVISIBLE);
        ObjectAnimator animation_main = ObjectAnimator.ofFloat(imageView_main, "translationX", -1800 * 2).setDuration(5000 * 2);
        ObjectAnimator animation_ch1_1 = ObjectAnimator.ofFloat(imageView_ch1_1, "translationX", -1800).setDuration(5000 * 2);
        ObjectAnimator animation_ch1_2 = ObjectAnimator.ofFloat(imageView_ch1_2, "translationX", 0).setDuration(5000 * 2);
        animation_main.start();
        animation_ch1_1.start();
        animation_ch1_2.start();
    }
}