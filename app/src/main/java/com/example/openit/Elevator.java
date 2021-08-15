package com.example.openit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Elevator extends AppCompatActivity {

    ImageView e_before;
    ImageView e_after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //가로로 변경
        setContentView(R.layout.activity_elevator);

        e_before = (ImageView)findViewById(R.id.ele_before);
        e_after = (ImageView)findViewById(R.id.ele_after);


    }

    public void noElevator(View view){
        e_before.setVisibility(View.INVISIBLE);
        e_after.setVisibility(View.VISIBLE);
    }

    public void yesStair(View view){
        Intent intent = new Intent(this, Stair.class);
        startActivity(intent);
    }




}