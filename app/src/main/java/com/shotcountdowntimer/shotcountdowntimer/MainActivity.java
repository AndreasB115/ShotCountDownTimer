package com.shotcountdowntimer.shotcountdowntimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtTotalTime, txtThirty, txtTwenty, txtFourteen, txtTwelve;
    private ImageView imgUp, imgDown, imgStart, imgRestart, imgFirst, imgSecond,imgPoint;

    private LinearLayout linBottom;
    private RelativeLayout relNum;
    private int totalNumber, num;
    private static Timer playingTimer;
    private boolean isPlaying, isOpenBottom;

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            initialSetup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialSetup() {
        setRefrences();
        setClickEvent();
    }

    private void setRefrences() {
        txtTotalTime = (TextView) findViewById(R.id.txtTotalTime);
        txtThirty = (TextView) findViewById(R.id.txtThirty);
        txtTwenty = (TextView) findViewById(R.id.txtTwenty);
        txtFourteen = (TextView) findViewById(R.id.txtFourteen);
        txtTwelve = (TextView) findViewById(R.id.txtTwelve);
        imgUp = (ImageView)findViewById(R.id.imgUp);
        imgDown = (ImageView)findViewById(R.id.imgDown);
        imgStart = (ImageView)findViewById(R.id.imgStart);
        imgRestart = (ImageView)findViewById(R.id.imgRestart);
        imgFirst = (ImageView)findViewById(R.id.imgFirst);
        imgSecond = (ImageView)findViewById(R.id.imgSecond);
        imgPoint = (ImageView)findViewById(R.id.imgPoint);
        relNum = (RelativeLayout) findViewById(R.id.relNum);
        linBottom = (LinearLayout) findViewById(R.id.linBottom);
        isPlaying = false;
        isOpenBottom = false;
        getNumbers();
        showNumber();
        showBorder();
    }

    private void setClickEvent() {
        txtTotalTime.setOnClickListener(this);
        txtThirty.setOnClickListener(this);
        txtTwenty.setOnClickListener(this);
        txtFourteen.setOnClickListener(this);
        txtTwelve.setOnClickListener(this);
        imgUp.setOnClickListener(this);
        imgDown.setOnClickListener(this);
        imgStart.setOnClickListener(this);
        imgRestart.setOnClickListener(this);

    }

    private void getNumbers(){

        linBottom.animate().translationY(linBottom.getHeight()).alpha(0.0f);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                linBottom.setVisibility(View.GONE);
            }
        }, 10);

        SharedPreferences prefs = this.getSharedPreferences(
                getPackageName(), Context.MODE_PRIVATE);
        int number = prefs.getInt("number", 0);
        if (number == 0)
        {
            totalNumber = 30;
        }
        else
            totalNumber = number;
        setTotalNumber();

    }

    private void setTotalNumber(){
        SharedPreferences prefs = this.getSharedPreferences(
                getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("number", totalNumber);
        editor.apply();
        num = totalNumber * 10;
        int txtNum = totalNumber;
        if (totalNumber == 14)
        {
            txtNum = 24;
        }
        boolean isShowing = false;
        if (totalNumber == 24 || totalNumber == 14)
        {
            isShowing = true;
        }
        showFourteen(isShowing);
        StringBuilder sb = new StringBuilder();
        sb.append(txtNum);
        sb.append(" ");
        sb.append("s");
        txtTotalTime.setText(sb.toString());
    }
    private  void showBorder()
    {
        if ( num == 0)
        {
            Drawable myDrawable;
            Resources res = getResources();
            try {
                myDrawable = Drawable.createFromXml(res, res.getXml(R.xml.border));
                relNum.setBackground(myDrawable);
                playAudio();
            } catch (Exception ex) {
                Log.e("Error", "Exception loading drawable");
            }
        }
        else
        {
            Drawable myDrawable;
            Resources res = getResources();
            try {
                myDrawable = Drawable.createFromXml(res, res.getXml(R.xml.borderdark));
                relNum.setBackground(myDrawable);
            } catch (Exception ex) {
                Log.e("Error", "Exception loading drawable");
            }
        }


    }

    private void playAudio()
    {
        mp = MediaPlayer.create(MainActivity.this, R.raw.sound);
        mp.start();
    }

    private void showNumber()
    {
        int numFirst = -1;
        int numSecond = -1;
        boolean isPoint = false;

        int cnt_value = 0;
        if (num > 99)
        {
            float value = num / 10.0f;
            int value1 = num % 10;
            if (num % 10 == 0)
            {
                cnt_value = (int) value;
            }
            else cnt_value = (int) value + 1;
        }
        else
        {
            cnt_value = num;
            isPoint = true;
        }

        numFirst = cnt_value / 10;
        numSecond = cnt_value % 10;

//        if (num < 10)
//        {
//            isPoint = true;
//            numFirst = 0;
//            numSecond = num;
//        }
//        else
//        {
//            int num1 = num / 100;
//            if (num1>0)
//            {
//                numFirst = num1;
//                numSecond = (num - 100*num1) / 10;
//            }
//            else
//            {
//                numFirst = num / 10;
//                numSecond = num % 10;
//                isPoint = true;
//            }
//        }


        if (numFirst == -1 || numSecond == -1) return;

        switch (numFirst)
        {
            case 0:
                imgFirst.setImageResource(R.drawable.r0);
                break;
            case 1:
                imgFirst.setImageResource(R.drawable.r1);
                break;
            case 2:
                imgFirst.setImageResource(R.drawable.r2);
                break;
            case 3:
                imgFirst.setImageResource(R.drawable.r3);
                break;
            case 4:
                imgFirst.setImageResource(R.drawable.r4);
                break;
            case 5:
                imgFirst.setImageResource(R.drawable.r5);
                break;
            case 6:
                imgFirst.setImageResource(R.drawable.r6);
                break;
            case 7:
                imgFirst.setImageResource(R.drawable.r7);
                break;
            case 8:
                imgFirst.setImageResource(R.drawable.r8);
                break;
            case 9:
                imgFirst.setImageResource(R.drawable.r9);
                break;
        }

        switch (numSecond)
        {
            case 0:
                imgSecond.setImageResource(R.drawable.r0);
                break;
            case 1:
                imgSecond.setImageResource(R.drawable.r1);
                break;
            case 2:
                imgSecond.setImageResource(R.drawable.r2);
                break;
            case 3:
                imgSecond.setImageResource(R.drawable.r3);
                break;
            case 4:
                imgSecond.setImageResource(R.drawable.r4);
                break;
            case 5:
                imgSecond.setImageResource(R.drawable.r5);
                break;
            case 6:
                imgSecond.setImageResource(R.drawable.r6);
                break;
            case 7:
                imgSecond.setImageResource(R.drawable.r7);
                break;
            case 8:
                imgSecond.setImageResource(R.drawable.r8);
                break;
            case 9:
                imgSecond.setImageResource(R.drawable.r9);
                break;
        }
        if (isPoint)
        {
            imgPoint.setVisibility(View.VISIBLE);
        }
        else imgPoint.setVisibility(View.INVISIBLE);
        showBorder();
    }

    private void plusNumber(){
        if (num >= totalNumber*10)
            return;
        num = num + 10;
        if (num > totalNumber * 10) num = totalNumber * 10;
        showNumber();

    }

    private void minusNumber(){
        if (num < 10)
            return;
        num = num - 10;
        showNumber();
    }

    private void play(){
        if (num < 0)
            return;
        isPlaying = !isPlaying;
        if (isPlaying)
        {
            goPlay();
        }
        else
        {
            stop();
        }
    }

    private void stop(){
        if (playingTimer != null)
        {
            isPlaying = false;
            imgStart.setImageResource(R.drawable.start);
            playingTimer.cancel();
            playingTimer.purge();
            playingTimer = null;
        }
    }

    private void goPlay()
    {
        imgStart.setImageResource(R.drawable.stop);

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
                playingTimer = new Timer();
                playingTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                num--;
                                showNumber();
                                if (num == 0)
                                {
                                    stop();
                                }


                            }

                        });
                    }
                }, 0, 100);
//            }
//        }, 1000);
    }

    private void restart(){
        if (totalNumber == 14) totalNumber = 24;
        playContinue();

    }

    private void playContinue()
    {
        if (isPlaying)
        {
            stop();
            num = totalNumber * 10;
            showNumber();
            isPlaying = true;
            goPlay();

        }
        else
        {
            num = totalNumber * 10;
            showNumber();
        }
    }

    private void onTotalTime(){
        isOpenBottom = !isOpenBottom;
        showBottomView();
    }

    private void showBottomView(){
        if (isOpenBottom)
        {
            linBottom.animate().translationY(0).alpha(1.0f);
            linBottom.setVisibility(View.VISIBLE);

        }
        else
        {
            linBottom.animate().translationY(linBottom.getHeight()).alpha(0.0f);
            linBottom.setVisibility(View.GONE);
        }


    }

    private void onThirty(){
        totalNumber = 30;
        clickNumbers();
    }

    private void onTwenty(){
        totalNumber = 24;
        clickNumbers();
    }

    private void onFourteen(){
        totalNumber = 14;
        playContinue();
    }

    private void onTwelve(){
        totalNumber = 12;
        clickNumbers();
    }

    private void clickNumbers(){
        stop();
        setTotalNumber();
        isOpenBottom = false;
        showBottomView();
        showNumber();
        boolean isShowing = false;
        if (totalNumber == 24 || totalNumber == 14)
        {
            isShowing = true;
        }
        else
        {
            isShowing = false;
        }
        showFourteen(isShowing);
    }

    private void showFourteen(Boolean isShowing)
    {
        if (isShowing)
        {
            txtFourteen.setVisibility(View.VISIBLE);
        }
        else
        {
            txtFourteen.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtTotalTime:
                onTotalTime();
                break;
            case R.id.txtThirty:
                onThirty();
                break;
            case R.id.txtTwenty:
                onTwenty();
                break;
            case R.id.txtFourteen:
                onFourteen();
                break;
            case R.id.txtTwelve:
                onTwelve();
                break;
            case R.id.imgUp:
                plusNumber();
                break;
            case R.id.imgDown:
                minusNumber();
                break;
            case R.id.imgStart:
                play();
                break;
            case R.id.imgRestart:
                restart();
                break;
        }
    }
}
