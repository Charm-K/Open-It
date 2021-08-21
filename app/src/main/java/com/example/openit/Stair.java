package com.example.openit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Stair extends AppCompatActivity {
    ImageView s_up;
    ImageView s_down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //가로로 변경
        setContentView(R.layout.activity_stair);
        s_up = (ImageView)findViewById(R.id.st_fireup);
        s_down = (ImageView)findViewById(R.id.st_firedown);
    }

    public void noUp(View view){
        fadeInAndHideImage(s_up);
         //s_up.setVisibility(View.VISIBLE);
    }
    public void noDown(View view){
        fadeInAndHideImage(s_down);
        //s_down.setVisibility(View.VISIBLE);
    }
    public void goDoor(View view){
        Intent intent = new Intent(this, Stubborn.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    private void fadeInAndHideImage(final ImageView img) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(300);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setVisibility(View.VISIBLE); }
                public void onAnimationRepeat(Animation animation) {}
                public void onAnimationStart(Animation animation) {}
        });
        img.startAnimation(fadeIn); }

}