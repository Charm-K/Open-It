package com.example.openit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Stair extends AppCompatActivity {
    ImageView s_up;
    ImageView s_down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //가로로 변경
        setContentView(R.layout.activity_stair);
        s_up = (ImageView)findViewById(R.id.st_fireup);
        s_down = (ImageView)findViewById(R.id.st_firedown);
    }

    public void noUp(View view){
         s_up.setVisibility(View.VISIBLE);
    }
    public void noDown(View view){
        s_down.setVisibility(View.VISIBLE);
    }
    public void goDoor(View view){
        Intent intent = new Intent(this, Stubborn.class);
        startActivity(intent);
    }
}