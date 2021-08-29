package com.example.openit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Stubborn extends AppCompatActivity {
    ImageView st_box1;
    ImageView st_box2;

    ImageView st_set1;
    ImageView st_set2;
    ImageView st_set;

    ImageView st_sup;

    ImageView st_player;

    public int st_step = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //가로로 변경
        setContentView(R.layout.activity_stubborn);
        st_box1 = (ImageView)findViewById(R.id.stub_box1);
        st_box2 = (ImageView)findViewById(R.id.stub_box2);
        st_set = (ImageView)findViewById(R.id.stub_set1);
        st_set1 = (ImageView)findViewById(R.id.stub_set1);
        st_set2 = (ImageView)findViewById(R.id.stub_set2);
        st_sup = (ImageView)findViewById(R.id.stub_support1);
        st_set1.setOnTouchListener(this::dragSet);
    }

    /*
    STEP1) 완강기 상자 열기
     */
    public void openBox(View view){
        if(st_step == 1){
            st_box1.setVisibility(View.INVISIBLE);
            st_box2.setVisibility(View.VISIBLE);
            fadeInAndHideImage(st_set1);
            st_step++;
        }
    }

    /*
    STEP2) 완강기 고리에 걸기
     */
    public boolean dragSet(View v, MotionEvent event) {
        float posX = st_set2.getX();
        float posY = st_set2.getY();
        float eventX = event.getRawX();
        float eventY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                st_set1.setX(eventX - st_set1.getWidth()/2); st_set1.setY(eventY - st_set1.getHeight()/2);
                float setX = st_set1.getX() + st_set1.getWidth()/2;
                float setY = st_set1.getY() + st_set1.getHeight()/2;
                if (st_step==2
                        &&setX >= posX && setY >= posY
                        && setX <= posX + st_set2.getWidth() && setY <= posY + st_set2.getHeight()/8) {
                    st_set1.setVisibility(View.INVISIBLE);
                    st_set = (ImageView)findViewById(R.id.stub_set2);
                    fadeInAndHideImage(st_set);
                    st_step++;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        return true;
    }

    /*
    STEP3) 지지대 바깥쪽으로 열기
     */
    public void openSupport(View view){
        if(st_step==3){
            st_set.setVisibility(View.INVISIBLE);
            st_set = (ImageView)findViewById(R.id.stub_set3);
            fadeInAndHideImage(st_set);
            st_sup.setVisibility(View.INVISIBLE);
            st_sup = (ImageView)findViewById(R.id.stub_support2);
            fadeInAndHideImage(st_sup);
            st_step++;
        }
    }

    /*
    STEP4) 릴을 창밖으로 던지기
     */
    public void set(View view){
        if(st_step == 4){
            st_set.setVisibility(View.INVISIBLE);
            st_set = (ImageView)findViewById(R.id.stub_set4);
            st_set.setVisibility(View.VISIBLE);
            st_player = (ImageView)findViewById(R.id.stub_player);
            fadeInAndHideImage(st_player);
            st_step++;
        }

    }

    /*
    STEP5) 가슴밸트 장착하기
    STEP6) 창밖으로 나가기
     */
    public void player(View view){
        if(st_step == 5){
            st_set.setVisibility(View.INVISIBLE);
            st_set = (ImageView)findViewById(R.id.stub_set5);
            st_set.setVisibility(View.VISIBLE);
            st_step++;
        }
        else if(st_step == 6){
            st_player.setVisibility(View.INVISIBLE);
            st_set.setVisibility(View.INVISIBLE);
            st_set = (ImageView)findViewById(R.id.stub_set6);
            st_set.setVisibility(View.VISIBLE);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goEscape();
                }
            }, 1000);

        }
    }


    public void goEscape(){
        Intent intent = new Intent(this, Escape.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        st_step = 1;
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