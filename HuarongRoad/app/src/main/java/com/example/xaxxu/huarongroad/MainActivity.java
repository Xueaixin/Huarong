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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mSteps = 0;
    private TextView mShowSteps;

    private ImageButton block1;
    private ImageButton block2;
    private boolean key;
    private View startView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowSteps = (TextView)findViewById(R.id.steps_num);

        block1 = (ImageButton)findViewById(R.id.block1);
        block2 = (ImageButton)findViewById(R.id.block2);

        key = true;
    }

    private int blockInRaw() {
        if(block1.getY() == block2.getY()) {
            if(block1.getX() == block2.getX() + block1.getWidth()){
                return 2;//block1在右边
            }
            if(block2.getX() == block1.getX() + block1.getWidth()) {
                return 1;//block1在左边
            }
        }
        return 0;//block没有挨着
    }

    private int blockInCol() {
        if(block1.getX() == block2.getX()) {
            if(block1.getY() == block2.getY() + block1.getHeight()){
                return 2;//block1在下边
            }
            if(block2.getY() == block1.getY() + block1.getHeight()) {
                return 1;//block1在上边
            }
        }
        return 0;//block没有挨着
    }

    private void isGetVictory() {
        ImageView caocaoImage = (ImageView) findViewById(R.id.caocao);
        TextView _exit = (TextView) findViewById(R.id.exit);

        if(caocaoImage.getX() == _exit.getX() &&
                caocaoImage.getY() == _exit.getY() - caocaoImage.getHeight()){
            victoryAlert();
        }
    }

    public void victoryAlert() {
        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(MainActivity.this);
        // Set the dialog title and message.
        myAlertBuilder.setTitle(R.string.alert_title);
        myAlertBuilder.setMessage(R.string.alert_message);
        // Add the dialog buttons.
        myAlertBuilder.setPositiveButton(R.string.new_button, new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked OK button.
                        mSteps = 0;
                        setContentView(R.layout.activity_main);
                        mShowSteps = (TextView)findViewById(R.id.steps_num);
                        startView = null;
                        key = true;
                    }
                });
        myAlertBuilder.setNegativeButton(R.string.cancel_button, new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled the dialog.
                        if(mShowSteps != null) {
                            mShowSteps.setText(Integer.toString(mSteps));
                        }
                    }
                });
        // Create and show the AlertDialog.
        myAlertBuilder.show();
    }

    public void showIntroduction(View view) {
        AlertDialog.Builder introAlert = new AlertDialog.Builder(MainActivity.this);
        introAlert.setTitle("游戏说明");
        introAlert.setMessage("华容道游戏取自著名的三国故事，曹操在赤壁大战中被刘备和孙权的“苦肉计”、“铁索连舟”打败，被迫退逃到华容道，又遇上诸葛亮的伏兵，关羽为了报答曹操对他的恩情，明逼实让，终于帮助曹操逃出了华容道。\n" +
                "\n通过移动各个棋子，帮助曹操从初始位置移到棋盘最下方中部，从出口逃走。不允许跨越棋子，还要设法用最少的步数把曹操移到出口。\n" +
                "\n快帮助曹操逃离华容道吧！\n");
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

//        ImageView mm = (ImageView)findViewById(R.id.zu);
//        Toast.makeText(getApplicationContext(), Integer.toString(mm.getLeft()) + mm.getTop(),
//                Toast.LENGTH_SHORT).show();

        String str = view.getClass().getSimpleName();
        if(key) {
            if(str.equals("AppCompatImageView")) {
                startView = view;
                startView.setPadding(5, 5, 5, 5);
                key = false;
            }
        }
        else {
            if(str.equals("AppCompatImageButton")) {
                if(startView.getWidth() == view.getWidth() * 2) {
                    boolean mkey = false;
                    if(startView.getX() == view.getX()) {
                        if((blockInRaw() == 1 && view.getX() == block1.getX()) || (blockInRaw() == 2 && view.getX() == block2.getX())){
                            mkey = true;
                        }
                    }
                    else if(startView.getX() == view.getX() - view.getWidth()) {
                        if((blockInRaw() == 1 && view.getX() == block2.getX()) || (blockInRaw() == 2 && view.getX() == block1.getX())){
                            mkey = true;
                        }
                    }
                    if(mkey) {
                        if(startView.getY() == view.getY() + view.getHeight()) {
                            startView.setY(startView.getY() - view.getHeight());
                            block1.setY(block1.getY() + startView.getHeight());
                            block2.setY(block2.getY() + startView.getHeight());
                            mSteps++;
                        }
                        else if(view.getY() == startView.getY() + startView.getHeight()) {
                            startView.setY(startView.getY() + view.getHeight());
                            block1.setY(block1.getY() - startView.getHeight());
                            block2.setY(block2.getY() - startView.getHeight());
                            mSteps++;
                        }
                        mkey = false;
                    }
                }
                if(startView.getHeight() == view.getHeight() * 2) {
                    boolean mmkey = false;
                    if(startView.getY() == view.getY()) {
                        if((blockInCol() == 1 && view.getY() == block1.getY()) || (blockInCol() == 2 && view.getY() == block2.getY())){
                            mmkey = true;
                        }
                    }
                    else if(startView.getY() == view.getY() - view.getHeight()) {
                        if((blockInCol() == 1 && view.getY() == block2.getY()) || (blockInCol() == 2 && view.getY() == block1.getY())){
                            mmkey = true;
                        }
                    }
                    if(mmkey) {
                        if(startView.getX() == view.getX() + view.getWidth()) {
                            startView.setX(startView.getX() - view.getWidth());
                            block1.setX(block1.getX() + startView.getWidth());
                            block2.setX(block2.getX() + startView.getWidth());
                            mSteps++;
                        }
                        else if(view.getX() == startView.getX() + startView.getWidth()) {
                            startView.setX(startView.getX() + view.getWidth());
                            block1.setX(block1.getX() - startView.getWidth());
                            block2.setX(block2.getX() - startView.getWidth());
                            mSteps++;
                        }
                        mmkey = false;
                    }
                }
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
        isGetVictory();
        if(mShowSteps != null) {
            mShowSteps.setText(Integer.toString(mSteps));
        }
    }
}
