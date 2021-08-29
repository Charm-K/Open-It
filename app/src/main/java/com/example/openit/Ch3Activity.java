package com.example.openit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Ch3Activity extends AppCompatActivity {
    private ImageView imageView_ch3_guide;
    private ImageView imageView_ch3_water;
    private ImageView imageView_ch3_towel;
    private ImageView imageView_ch3_waterOfTowel;
    private boolean watertapIsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ch3);
        imageView_ch3_guide = (ImageView)findViewById(R.id.imageView_ch3_guide);
        imageView_ch3_water = (ImageView)findViewById(R.id.imageView_ch3_water);
        imageView_ch3_towel = (ImageView)findViewById(R.id.imageView_ch3_towel);
        imageView_ch3_waterOfTowel = (ImageView)findViewById(R.id.imageView_ch3_waterOfTowel);
        imageView_ch3_towel.setOnTouchListener(this::onTouchTowel);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fadeOutAndHideImage(imageView_ch3_guide);
            }
        }, 2000);
    }

    public void onClickWaterTap(View v) {
        watertapIsOpen = !watertapIsOpen;
        imageView_ch3_water.setVisibility((watertapIsOpen)?View.VISIBLE:View.INVISIBLE);
    }

    public boolean onTouchTowel(View v, MotionEvent event) {
        float waterX = imageView_ch3_water.getX();
        float waterY = imageView_ch3_water.getY();
        float eventX = event.getRawX();
        float eventY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                imageView_ch3_towel.setX(eventX - imageView_ch3_towel.getWidth()/2); imageView_ch3_towel.setY(eventY - imageView_ch3_towel.getHeight()/2);
                imageView_ch3_waterOfTowel.setX(eventX - imageView_ch3_towel.getWidth()/3); imageView_ch3_waterOfTowel.setY(eventY - imageView_ch3_towel.getHeight()/2);

                float towelX = imageView_ch3_towel.getX() + imageView_ch3_towel.getWidth()/2;
                float towelY = imageView_ch3_towel.getY() + imageView_ch3_towel.getHeight()/2;
                if (watertapIsOpen
                        && towelX >= waterX && towelY >= waterY
                        && towelX <= waterX + imageView_ch3_water.getWidth() && towelY <= waterY + imageView_ch3_water.getHeight()) {
                    fadeInAndHideImage(imageView_ch3_waterOfTowel);
                    Intent intent4 = new Intent(this, Ch4Activity.class);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent4);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            finish();
                        }
                    }, 400);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        return true;
    }

    private void fadeInAndHideImage(final ImageView img) { //이미지뷰 페이드인
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(500);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setVisibility(View.VISIBLE); }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        img.startAnimation(fadeIn);
    }
    private void fadeOutAndHideImage(final ImageView img) { //이미지뷰 페이드아웃
        Animation fadeIn = new AlphaAnimation(1, 0);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(500);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setVisibility(View.INVISIBLE); }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        img.startAnimation(fadeIn);
    }
}
