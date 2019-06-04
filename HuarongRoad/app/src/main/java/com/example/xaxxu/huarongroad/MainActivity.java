package com.example.xaxxu.huarongroad;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mSteps = 0;
    private TextView mShowSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowSteps = (TextView)findViewById(R.id.steps_num);
    }

    public void showIntroduction(View view) {
        AlertDialog.Builder introAlert = new AlertDialog.Builder(MainActivity.this);
        introAlert.setTitle("游戏说明");
        introAlert.setMessage("帮助曹操逃脱！");
        introAlert.show();
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void changeSteps(View view) {
        mSteps++;
        if(mShowSteps != null) {
            mShowSteps.setText(Integer.toString(mSteps));
        }
    }
}
