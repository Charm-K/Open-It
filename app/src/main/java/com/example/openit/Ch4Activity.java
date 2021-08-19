package com.example.openit;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Ch4Activity extends AppCompatActivity {
    private ImageView imageView_ch4_background;
    private ImageView imageView_ch4_background2;
    private ImageView imageView_ch4_safetypin;
    private ImageView imageView_ch4_wind;
    private ImageView imageView_ch4_rotationArrow;
    private ImageView imageView_ch4_fireup;
    private int fireup_alpha = 256;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ch4);

        imageView_ch4_background = (ImageView)findViewById(R.id.imageView_ch4_background);
        imageView_ch4_background2 = (ImageView)findViewById(R.id.imageView_ch4_background2);
        imageView_ch4_safetypin = (ImageView)findViewById(R.id.imageView_ch4_safetypin);
        imageView_ch4_wind = (ImageView)findViewById(R.id.imageView_ch4_wind);
        imageView_ch4_rotationArrow = (ImageView)findViewById(R.id.imageView_ch4_rotationArrow);
        imageView_ch4_fireup = (ImageView)findViewById(R.id.imageView_ch4_fireup);
        imageView_ch4_fireup.setOnTouchListener(this::onTouchFireup);
    }

    public void onClickSafetypin(View v) {
        imageView_ch4_safetypin.setVisibility(View.INVISIBLE);
        imageView_ch4_wind.setVisibility(View.VISIBLE);
        imageView_ch4_rotationArrow.setVisibility(View.VISIBLE);
    }

    public void onClickRotationArrow(View v) {
        imageView_ch4_rotationArrow.setVisibility(View.INVISIBLE);
        imageView_ch4_background.setVisibility(View.INVISIBLE);
        imageView_ch4_background2.setVisibility(View.VISIBLE);
    }

    public boolean onTouchFireup(View v, MotionEvent event) {
        float eventX = event.getRawX();
        float eventY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                fireup_alpha -= Math.random() % 40 + 1;
                fireup_alpha = (fireup_alpha < 0)?0:fireup_alpha;
                imageView_ch4_fireup.setImageAlpha(fireup_alpha);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        return true;
    }
}