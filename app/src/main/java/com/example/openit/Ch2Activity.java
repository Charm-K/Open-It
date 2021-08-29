package com.example.openit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Ch2Activity extends AppCompatActivity {
    public ImageView c2_guide;
    public ImageView c2_button;
    public ImageView c2_ment;
    public ImageView c2_human;
    SoundPool soundPool;
    int soundID;
    Animation animTransRight;
    public boolean check_on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch2);
        c2_guide = (ImageView)findViewById(R.id.ch2_guide);
        c2_button = (ImageView)findViewById(R.id.ch2_button);
        c2_ment = (ImageView)findViewById(R.id.ch2_ment);
        c2_human = (ImageView)findViewById(R.id.ch2_player);
        soundPool = new SoundPool(1,AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(this,R.raw.siren_sound,0);
        animTransRight = AnimationUtils.loadAnimation(this,R.anim.anim_translate_right);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fadeOutAndHideImage(c2_guide);
            }
        }, 2000);
    }

    public void pushBell(View v){
        if(check_on==false){
            check_on = true;
            fadeInAndHideImage(c2_ment);
            soundPool.play(soundID,1f,1f,0,0,1.1f);

            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fadeInAndHideImage(c2_human);
                    c2_human.startAnimation(animTransRight);
                }
            }, 2000);

            Intent intent = new Intent(this, Ch3Activity.class);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }, 5000);
        }

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