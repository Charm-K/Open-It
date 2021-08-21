package com.example.openit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Final extends AppCompatActivity{

    public EditText f_edit;
    public ImageView f_editname;
    public ImageView f_editbut;

    public ImageView f_back;
    public ImageView f_show;
    public ImageView f_home;
    public ImageView f_warning;
    public TextView f_name;
    public TextView f_time;
    public TextView f_text1;
    public TextView f_text2;
    public TextView f_text3;

    public String name;
    public int f_step = 1;

    //시간 가져오기
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String getTime = dateFormat.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //가로로 변경
        setContentView(R.layout.activity_final);

        f_name = (TextView)findViewById(R.id.fin_name);
        f_time = (TextView)findViewById(R.id.fin_time);
        f_text1 = (TextView)findViewById(R.id.fin_text1);
        f_text2 = (TextView)findViewById(R.id.fin_text2);
        f_text3 = (TextView)findViewById(R.id.fin_text3);

        f_time = (TextView)findViewById(R.id.fin_time);
        f_back = (ImageView)findViewById(R.id.fin_back2);
        f_show = (ImageView)findViewById(R.id.fin_show);
        f_home = (ImageView)findViewById(R.id.fin_home);
        f_warning = (ImageView)findViewById(R.id.fin_warning);

        f_edit = (EditText)findViewById(R.id.fin_edit);
        f_editname = (ImageView)findViewById(R.id.fin_editname);
        f_editbut = (ImageView)findViewById(R.id.fin_editbut);

    }

    public void click(View view){
        if(f_step==1){
            fadeInAndHideImage(f_back);
            fadeInAndHideImage(f_editname);
            fadeInAndHideImage(f_editbut);
            f_edit.setVisibility(View.VISIBLE);
            f_step++;
        }
    }


    public void getName(View view){
        if ( f_edit.getText().toString().length() == 0 ) {
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();

        } else {
            name = f_edit.getText().toString();
            f_edit.setVisibility(View.INVISIBLE);
            f_editbut.setVisibility(View.INVISIBLE);
            f_editname.setVisibility(View.INVISIBLE);

            //결과 화면 뜨게하기
            setShow();
        }
        
    }

    public void setShow(){
        f_name.setText(name);
        f_time.setText(getTime);
        fadeInAndHideImage(f_show);
        fadeInAndHideImage(f_home);
        fadeInAndHideImage(f_warning);
        textfadeInAndHideImage(f_name);
        textfadeInAndHideImage(f_time);
        textfadeInAndHideImage(f_text1);
        textfadeInAndHideImage(f_text2);
        textfadeInAndHideImage(f_text3);
        name = null;
    }

    public void goHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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

    private void textfadeInAndHideImage(final TextView img) { //텍스트뷰 페이드인
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