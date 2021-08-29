package com.example.openit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Escape extends AppCompatActivity {

    public ImageView esc_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escape);
        esc_player = (ImageView)findViewById(R.id.esc_player);
        esc_player.setOnTouchListener(this::onTouchPlayerDown);
    }

    public boolean onTouchPlayerDown(View v, MotionEvent event) {
        float eventX = event.getRawX();
        float eventY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                if(esc_player.getY()>=-140){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            goFinal();
                        }
                    }, 500);
                }
                else{
                    esc_player.setY(esc_player.getY() +2);
                }
                //Log.d("HI", String.valueOf(esc_player.getY()));
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        return true;
    }

    public void goFinal(){
        Intent intent = new Intent(this, Final.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

}