package com.example.openit;

import android.content.Intent;
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
    private ImageView imageView_ch4_powder;
    private int fireup_alpha = 256;
    private boolean fireExtinguisherIsRotate = false;

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
        imageView_ch4_powder = (ImageView)findViewById(R.id.imageView_ch4_powder);
        imageView_ch4_fireup.setOnTouchListener(this::onTouchFireup);
        imageView_ch4_powder.setImageAlpha(0);
    }

    public void onClickSafetypin(View v) {
        imageView_ch4_safetypin.setVisibility(View.INVISIBLE);
        imageView_ch4_wind.setVisibility(View.VISIBLE);
        imageView_ch4_rotationArrow.setVisibility(View.VISIBLE);
    }

    public void onClickRotationArrow(View v) {
        fireExtinguisherIsRotate = true;
        imageView_ch4_rotationArrow.setVisibility(View.INVISIBLE);
        imageView_ch4_background.setVisibility(View.INVISIBLE);
        imageView_ch4_background2.setVisibility(View.VISIBLE);
    }

    public boolean onTouchFireup(View v, MotionEvent event) {
        float eventX = event.getRawX();
        float eventY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!fireExtinguisherIsRotate) return false;
                return true;
            case MotionEvent.ACTION_MOVE:
                fireup_alpha -= Math.random() % 40 + 1;
                //fireup_alpha = (fireup_alpha < 0)?0:fireup_alpha;
                if(fireup_alpha <= 0){
                    Intent intent = new Intent(this, Elevator.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
                imageView_ch4_fireup.setImageAlpha(fireup_alpha);
                imageView_ch4_powder.setImageAlpha(128 - fireup_alpha);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        return true;
    }
}
