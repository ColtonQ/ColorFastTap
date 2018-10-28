package com.notexample.coltonquan.colorblock;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

import android.os.CountDownTimer;
import android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    TextView timerText;

    public static CountDownTimer countDownTimer;

    private static final String FORMAT = "%02d:%02d:%02d";

    int seconds, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Setting widths and heights
        TextView bubbleText = (TextView) findViewById(R.id.bubbleText);
        TextView jetText = (TextView)findViewById(R.id.jetText);
        //ImageView jetImage = (ImageView)findViewById(R.id.jetImage);
        //ImageView bubblesImage = (ImageView)findViewById(R.id.bubblesImage);

        Resources r = getResources();

        //int bubbleTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,r.getDisplayMetrics());
        //bubbleText.setTextSize(bubbleTextPx);

        //int jetTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,r.getDisplayMetrics());
        //jetText.setTextSize(jetTextPx);

        //int jetImageWidthPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
        //int jetImageHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,r.getDisplayMetrics());

        //RelativeLayout.LayoutParams jetImagelayoutParams = new RelativeLayout.LayoutParams(jetImageWidthPx, jetImageHeightPx);
        //jetImage.setLayoutParams(jetImagelayoutParams);

        //int bubblesImageWidthPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
        //int bubblesImageHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,r.getDisplayMetrics());

        //RelativeLayout.LayoutParams bubblesImagelayoutParams = new RelativeLayout.LayoutParams(bubblesImageWidthPx, bubblesImageHeightPx);
        //bubblesImage.setLayoutParams(bubblesImagelayoutParams);

        bubbleText.setTextColor(Color.BLUE);
        jetText.setTextColor(Color.MAGENTA);

        timerText = (TextView)findViewById(R.id.timerText);

        countDownTimer = new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {

                timerText.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish(){
                Intent i = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }


        }.start();

    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
