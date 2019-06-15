package com.example.xaxxu.huarongroad;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mSteps = 0;
    private TextView mShowSteps;

    private boolean key;
    private View startView;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowSteps = (TextView)findViewById(R.id.steps_num);

        key = true;
    }

    public void showIntroduction(View view) {
        AlertDialog.Builder introAlert = new AlertDialog.Builder(MainActivity.this);
        introAlert.setTitle("游戏说明");
        introAlert.setMessage("帮助曹操逃脱！");
        introAlert.show();
    }

    public void reStart(View view) {
        mSteps = 0;
        setContentView(R.layout.activity_main);
        mShowSteps = (TextView)findViewById(R.id.steps_num);
        startView = null;
        key = true;
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void changeSteps(View view) {
//        Toast.makeText(getApplicationContext(), "start:" + view.getClass().getSimpleName(),
//                Toast.LENGTH_SHORT).show();
//        if(startView != null) {
//            Toast.makeText(getApplicationContext(), "start:" + startView.getId(),
//                    Toast.LENGTH_SHORT).show();
//        }
        str = view.getClass().getSimpleName();
        if(key) {
            if(str.equals("AppCompatImageView")) {
                startView = view;
                startView.setPadding(5, 5, 5, 5);
                key = false;
            }
        }
        else {
            if(str.equals("AppCompatImageButton")) {
//                Toast.makeText(getApplicationContext(), Integer.toString(startView.getTop()),
//                        Toast.LENGTH_SHORT).show();
                if((startView.getWidth() == view.getWidth()) && (startView.getX() == view.getX())) {

                    if(startView.getY() == view.getY() + view.getHeight()) {
                        startView.setY(startView.getY() - view.getHeight());
                        view.setY(view.getY() + startView.getHeight());
                        mSteps++;
                    }
                    else if(view.getY() == startView.getY() + startView.getHeight()) {
                        startView.setY(startView.getY() + view.getHeight());
                        view.setY(view.getY() - startView.getHeight());
                        mSteps++;
                    }
                }
                if((startView.getHeight() == view.getHeight()) && (startView.getY() == view.getY())) {
                    if(startView.getX() == view.getX() + view.getWidth()) {
                        startView.setX(startView.getX() - view.getWidth());
                        view.setX(view.getX() + startView.getWidth());
                        mSteps++;
                    }
                    else if(view.getX() == startView.getX() + startView.getWidth()) {
                        startView.setX(startView.getX() + view.getWidth());
                        view.setX(view.getX() - startView.getWidth());
                        mSteps++;
                    }
                }
            }
            startView.setPadding(0, 0, 0, 0);
            startView = null;
            key = true;
            str = "";
        }
        if(mShowSteps != null) {
            mShowSteps.setText(Integer.toString(mSteps));
        }
    }
}
