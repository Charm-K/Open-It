package com.example.openit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Elevator extends AppCompatActivity {

    ImageView e_before;
    ImageView e_after;
    ImageView e_door;
    ImageView e_degree;
    public boolean check_deg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator);

        e_before = (ImageView)findViewById(R.id.ele_before);
        e_after = (ImageView)findViewById(R.id.ele_after);
        e_door = (ImageView)findViewById(R.id.ele_door);
        e_degree = (ImageView)findViewById(R.id.ele_temp);
        e_degree.setOnTouchListener(this::checkDegree);

    }

    public void noElevator(View view){
        e_before.setVisibility(View.INVISIBLE);
        fadeInAndHideImage(e_after);
    }

    public void yesStair(View view){
        if(check_deg==true){
            Intent intent = new Intent(this, Stair.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            check_deg=false;
        }

    }

    public boolean checkDegree(View v, MotionEvent event) {
        float posX = e_door.getX();
        float posY = e_door.getY();
        float eventX = event.getRawX();
        float eventY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                e_degree.setX(eventX - e_degree.getWidth()/2); e_degree.setY(eventY - e_degree.getHeight()/2);
                float degX = e_degree.getX();
                float degY = e_degree.getY();
                if (degX >= posX && degY >= posY
                        && degX <= posX + e_door.getWidth() && degY <= posY + e_door.getHeight()) {
                    e_degree.setImageResource(R.drawable.elevator_temp2);
                    check_deg=true;
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


}