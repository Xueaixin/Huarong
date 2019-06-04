package com.example.xaxxu.huarongroad;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showIntroduction(View view) {
        AlertDialog.Builder introAlert = new AlertDialog.Builder(MainActivity.this);
        introAlert.setTitle("游戏说明");
        introAlert.setMessage("帮助曹操逃脱！");
        introAlert.show();
    }
}
