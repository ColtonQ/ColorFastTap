package com.notexample.coltonquan.colorblock;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.graphics.Color;
import android.content.Intent;
import android.view.View;
import java.util.concurrent.TimeUnit;
import android.os.CountDownTimer;
import android.content.res.Resources;
import android.util.TypedValue;

public class PlayScreen extends AppCompatActivity {

    public static int counter = 0;
    public static CountDownTimer countDownTimer;

    TextView gameTimerText;
    String mode;
    Integer seconds;

    private static final String FORMAT = "%02d:%02d";
    private static final String FORMAT1 = "%02d:" + 0 + "%02d";

    //int seconds, minutes;

    //boolean finish;

    public static final String prefsName = "myPrefsFile";
    public static boolean colorblind;

    public void nextLevel() {
        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);

        List<Integer> list = new ArrayList<>();


        SharedPreferences prefs = getSharedPreferences(prefsName, MODE_PRIVATE);
        colorblind = prefs.getBoolean("colorblindString", false);

        if (colorblind) {
            list.add(Color.BLACK);
            list.add(0xffEEEEEE);
            list.add(0xffBBBBBB);
            //list.add(0xff999999);
            list.add(0xff777777);
            //list.add(0xff555555);
            list.add(0xff333333);

        } else {
            list.add(Color.BLACK);
            list.add(Color.BLUE);
            list.add(Color.CYAN);
            list.add(Color.GREEN);
            list.add(Color.MAGENTA);
            list.add(Color.RED);
            list.add(Color.YELLOW);
        }

        Random randomizer = new Random();

        //TextView gameTimerText = (TextView)findViewById(R.id.gameTimerText);
        //gameTimerText.setText(random);

        List<Object> buttonList = new ArrayList<>();
        buttonList.add(topLeft);
        buttonList.add(topRight);
        buttonList.add(bottomLeft);
        buttonList.add(bottomRight);

        while (true) {

            Integer num;
            num = 0;

            Integer color1 = list.get(randomizer.nextInt(list.size()));
            Integer color2 = list.get(randomizer.nextInt(list.size()));
            Integer color3 = list.get(randomizer.nextInt(list.size()));
            Integer color4 = list.get(randomizer.nextInt(list.size()));

            List<Integer> colorList = new ArrayList<>();
            colorList.add(color1);
            colorList.add(color2);
            colorList.add(color3);
            colorList.add(color4);

            for (num = 0; num < 4; num++) {
                if (num == 0) {
                    topLeft.setBackgroundColor(colorList.get(num));
                }
                if (num == 1) {
                    topRight.setBackgroundColor(colorList.get(num));
                }
                if (num == 2) {
                    bottomLeft.setBackgroundColor(colorList.get(num));
                }
                if (num == 3) {
                    bottomRight.setBackgroundColor(colorList.get(num));
                }

            }

            ColorDrawable buttonColor1 = (ColorDrawable) topLeft.getBackground();
            Integer buttonColorId1 = buttonColor1.getColor();
            ColorDrawable buttonColor2 = (ColorDrawable) topRight.getBackground();
            Integer buttonColorId2 = buttonColor2.getColor();
            ColorDrawable buttonColor3 = (ColorDrawable) bottomLeft.getBackground();
            Integer buttonColorId3 = buttonColor3.getColor();
            ColorDrawable buttonColor4 = (ColorDrawable) bottomRight.getBackground();
            Integer buttonColorId4 = buttonColor4.getColor();


            ImageButton masterButton = (ImageButton) findViewById(R.id.masterButton);
            Integer masterColor = colorList.get(randomizer.nextInt(colorList.size()));
            masterButton.setBackgroundColor(masterColor);

            if (!buttonColorId1.equals(buttonColorId2) && !buttonColorId1.equals(buttonColorId3) &&
                    !buttonColorId1.equals(buttonColorId4) && !buttonColorId2.equals(buttonColorId3) &&
                    !buttonColorId2.equals(buttonColorId4) && !buttonColorId3.equals(buttonColorId4)) {
                break;
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        counter = 0;

        setContentView(R.layout.activity_play_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView counterText = (TextView) findViewById(R.id.counterText);

        counterText.setText(String.valueOf(counter));

        gameTimerText = (TextView) findViewById(R.id.gameTimerText);

        //nextLevel();

        Bundle modeType = getIntent().getExtras();
        if (modeType == null) {
            return;
        }
        mode = modeType.getString("modeString");

        //Setting widths and heights

        final ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
        final ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
        final ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
        final ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
        final ImageButton masterButton = (ImageButton) findViewById(R.id.masterButton);
        final TextView loseText = (TextView) findViewById(R.id.loseText);
        final TextView gameTimerText = (TextView) findViewById(R.id.gameTimerText);

        Resources r = getResources();

        //int topLeftPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        //int topRightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        //int bottomLeftPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        //int bottomRightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        //RelativeLayout.LayoutParams topLeftLayoutParams = new RelativeLayout.LayoutParams(topLeftPx, topLeftPx);
        //topLeft.setLayoutParams(topLeftLayoutParams);
        //RelativeLayout.LayoutParams topRightLayoutParams = new RelativeLayout.LayoutParams(topRightPx, topRightPx);
        //topLeft.setLayoutParams(topRightLayoutParams);
        //RelativeLayout.LayoutParams bottomLeftLayoutParams = new RelativeLayout.LayoutParams(bottomLeftPx, bottomRightPx);
        //topLeft.setLayoutParams(bottomLeftLayoutParams);
        //RelativeLayout.LayoutParams bottomRightLayoutParams = new RelativeLayout.LayoutParams(bottomRightPx, bottomRightPx);
        //topLeft.setLayoutParams(bottomRightLayoutParams);





        //int masterButtonWidthPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
        //int masterButtonHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, r.getDisplayMetrics());
        //RelativeLayout.LayoutParams masterButtonLayoutParams = new RelativeLayout.LayoutParams(masterButtonWidthPx, masterButtonHeightPx);
        //topLeft.setLayoutParams(masterButtonLayoutParams);

        int gameTimerTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, r.getDisplayMetrics());
        int counterTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, r.getDisplayMetrics());
        gameTimerText.setTextSize(gameTimerTextPx);
        counterText.setTextSize(counterTextPx);

        int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
        loseText.setTextSize(loseTextPx);

        ImageView homeImage = (ImageView)findViewById(R.id.homeImage);

        int homeImagePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,r.getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(homeImagePx, homeImagePx);
        homeImage.setLayoutParams(layoutParams);
        int colorWhite = Color.parseColor("#FFFFFF");
        homeImage.setColorFilter(colorWhite);
        homeImage.setEnabled(false);

        if (mode.equals("zen")){
            int colorGrey = Color.parseColor("#CECECE");
            homeImage.setColorFilter(colorGrey);
            homeImage.setEnabled(true);
        }

        //int loseTextSidePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        //loseText.setWidth(loseTextSidePx);
        //loseText.setHeight(loseTextSidePx);

        //End of setting widths and heights

        topLeft.setEnabled(false);
        topRight.setEnabled(false);
        bottomLeft.setEnabled(false);
        bottomRight.setEnabled(false);

        gameTimerText.setTextColor(Color.TRANSPARENT);


        loseText.setBackgroundColor(Color.LTGRAY);
        loseText.setText("TAP TO START");
        topLeft.setBackgroundColor(Color.GRAY);
        topRight.setBackgroundColor(Color.GRAY);
        bottomLeft.setBackgroundColor(Color.GRAY);
        bottomRight.setBackgroundColor(Color.GRAY);
        masterButton.setBackgroundColor(Color.GRAY);
    }

    public void onClickHomeImage(View view){
        Intent iHome = new Intent(PlayScreen.this, HomeScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClickStartGame(View view) {
        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
        TextView loseText = (TextView) findViewById(R.id.loseText);
        topLeft.setEnabled(true);
        topRight.setEnabled(true);
        bottomLeft.setEnabled(true);
        bottomRight.setEnabled(true);
        loseText.setEnabled(false);
        loseText.setTextSize(0);
        loseText.setTextColor(Color.TRANSPARENT);
        loseText.setBackgroundColor(Color.TRANSPARENT);
        if (!mode.equals("zen")) {
            gameTimerText.setTextColor(Color.BLACK);
        }
        nextLevel();

        TextView secondsText = (TextView) findViewById(R.id.secondsText);
        if (mode.equals("classic")) {
            secondsText.setTextColor(Color.BLACK);
            secondsText.setText("1500 ms");
        }

        if (mode.equals("timed")) {
            seconds = 21000;
        } else if (mode.equals("classic")) {
            seconds = 1500;
        } else if (mode.equals("zen")) {
            seconds = 999999;
        }

        if (!mode.equals("zen")) {
            countDownTimer = new CountDownTimer(seconds, 50) {

                public void onTick(long millisUntilFinished) {

                    if (mode.equals("timed")) {
                        if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) < 5) {
                            gameTimerText.setTextColor(0xffff4500);
                        }
                    } else if (mode.equals("classic")) {
                        if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == 1) {
                            gameTimerText.setTextColor(Color.GREEN);
                        } else if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) < 500) {
                            gameTimerText.setTextColor(Color.RED);
                        } else {
                            gameTimerText.setTextColor(0xffffa500);
                        }
                    }

                    if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) >= 100) {
                        gameTimerText.setText("" + String.format(FORMAT,

                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                )));
                    } else {
                        gameTimerText.setText("" + String.format(FORMAT1,

                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                )));
                    }
                }

                public void onFinish() {
                    ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
                    ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
                    ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
                    ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
                    topLeft.setEnabled(false);
                    topRight.setEnabled(false);
                    bottomLeft.setEnabled(false);
                    bottomRight.setEnabled(false);

                    gameTimerText.setText("00:000");
                    gameTimerText.setTextColor(Color.RED);
                    final TextView loseText = (TextView) findViewById(R.id.loseText);
                    loseText.setBackgroundColor(Color.LTGRAY);
                    loseText.setTextColor(Color.BLACK);
                    loseText.setText("TIME'S UP");

                    Resources r = getResources();
                    int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                    loseText.setTextSize(loseTextPx);

                    new CountDownTimer(2000, 1000) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            final String score = String.valueOf(counter);
                            String endScreenLoseText = loseText.getText().toString();

                            Intent i = new Intent(PlayScreen.this, EndScreen.class);
                            i.putExtra("score", score);
                            i.putExtra("endScreenLoseText", endScreenLoseText);
                            i.putExtra("modeString", mode);
                            startActivity(i);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }


                    }.start();
                }


            }.start();

        }
    }

    @Override
    public void onBackPressed() {
        if (!mode.equals("zen")) {
            countDownTimer.cancel();
        }
        Intent iHome = new Intent(PlayScreen.this, HomeScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClickTopLeft(View view) {
        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
        ImageButton masterButton = (ImageButton) findViewById(R.id.masterButton);
        TextView loseText = (TextView) findViewById(R.id.loseText);

        ColorDrawable buttonColor = (ColorDrawable) topLeft.getBackground();
        Integer buttonColorId = buttonColor.getColor();
        ColorDrawable masterColor = (ColorDrawable) masterButton.getBackground();
        Integer masterColorId = masterColor.getColor();

        TextView counterText = (TextView) findViewById(R.id.counterText);


        if (buttonColorId.equals(masterColorId)) {
            nextLevel();
            counter++;
            counterText.setText(String.valueOf(counter));

            TextView secondsText = (TextView) findViewById(R.id.secondsText);
            if (mode.equals("classic")) {
                countDownTimer.cancel();
                if (counter <= 50 && counter % 10 == 0) {
                    seconds = seconds - 200;
                    secondsText.setText(seconds.toString() + " ms");
                }
                countDownTimer = new CountDownTimer(seconds, 50) {

                    public void onTick(long millisUntilFinished) {

                        if (mode.equals("timed")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) < 5) {
                                gameTimerText.setTextColor(0xffff4500);
                            }
                        } else if (mode.equals("classic")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == 1) {
                                gameTimerText.setTextColor(Color.GREEN);
                            } else if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) < 500) {
                                gameTimerText.setTextColor(Color.RED);
                            } else {
                                gameTimerText.setTextColor(0xffffa500);
                            }
                        }

                        if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) >= 100) {
                            gameTimerText.setText("" + String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        } else {
                            gameTimerText.setText("" + String.format(FORMAT1,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        }
                    }

                    public void onFinish() {
                        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
                        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
                        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
                        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
                        topLeft.setEnabled(false);
                        topRight.setEnabled(false);
                        bottomLeft.setEnabled(false);
                        bottomRight.setEnabled(false);

                        gameTimerText.setText("00:000");
                        gameTimerText.setTextColor(Color.RED);
                        final TextView loseText = (TextView) findViewById(R.id.loseText);
                        loseText.setBackgroundColor(Color.LTGRAY);
                        loseText.setTextColor(Color.BLACK);
                        loseText.setText("TIME'S UP");

                        Resources r = getResources();
                        int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                        loseText.setTextSize(loseTextPx);

                        new CountDownTimer(2000, 1000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                final String score = String.valueOf(counter);
                                String endScreenLoseText = loseText.getText().toString();

                                Intent i = new Intent(PlayScreen.this, EndScreen.class);
                                i.putExtra("score", score);
                                i.putExtra("endScreenLoseText", endScreenLoseText);
                                i.putExtra("modeString", mode);
                                startActivity(i);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }


                        }.start();
                    }


                }.start();
            }
        } else {
            //TextView loseText = (TextView)findViewById(R.id.loseText);
            if (!mode.equals("zen")) {
                loseText.setText("GAME OVER");
                loseText.setTextColor(Color.BLACK);
                loseText.setBackgroundColor(Color.LTGRAY);
                topLeft.setEnabled(false);
                topRight.setEnabled(false);
                bottomLeft.setEnabled(false);
                bottomRight.setEnabled(false);

                Resources r = getResources();
                int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                loseText.setTextSize(loseTextPx);


                countDownTimer.cancel();

                final String score = String.valueOf(counter);
                final String endScreenLoseText = loseText.getText().toString();

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        Intent i = new Intent(PlayScreen.this, EndScreen.class);
                        i.putExtra("score", score);
                        i.putExtra("endScreenLoseText", endScreenLoseText);
                        i.putExtra("modeString", mode);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }


                }.start();
            }else{
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        gameTimerText.setTextColor(Color.RED);
                        gameTimerText.setText("Try Again");
                    }

                    public void onFinish(){
                        gameTimerText.setTextColor(Color.WHITE);
                    }
                }.start();
            }
        }
    }

    public void onClickTopRight(View view) {
        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
        ImageButton masterButton = (ImageButton) findViewById(R.id.masterButton);
        TextView loseText = (TextView) findViewById(R.id.loseText);

        ColorDrawable buttonColor = (ColorDrawable) topRight.getBackground();
        Integer buttonColorId = buttonColor.getColor();
        ColorDrawable masterColor = (ColorDrawable) masterButton.getBackground();
        Integer masterColorId = masterColor.getColor();

        TextView counterText = (TextView) findViewById(R.id.counterText);

        if (buttonColorId.equals(masterColorId)) {
            nextLevel();
            counter++;
            counterText.setText(String.valueOf(counter));

            TextView secondsText = (TextView) findViewById(R.id.secondsText);
            if (mode.equals("classic")) {
                countDownTimer.cancel();
                if (counter <= 50 && counter % 10 == 0) {
                    seconds = seconds - 200;
                    secondsText.setText(seconds.toString() + " ms");
                }
                countDownTimer = new CountDownTimer(seconds, 50) {

                    public void onTick(long millisUntilFinished) {

                        if (mode.equals("timed")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) < 5) {
                                gameTimerText.setTextColor(0xffff4500);
                            }
                        } else if (mode.equals("classic")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == 1) {
                                gameTimerText.setTextColor(Color.GREEN);
                            } else if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) < 500) {
                                gameTimerText.setTextColor(Color.RED);
                            } else {
                                gameTimerText.setTextColor(0xffffa500);
                            }
                        }

                        if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) >= 100) {
                            gameTimerText.setText("" + String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        } else {
                            gameTimerText.setText("" + String.format(FORMAT1,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        }
                    }

                    public void onFinish() {
                        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
                        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
                        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
                        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
                        topLeft.setEnabled(false);
                        topRight.setEnabled(false);
                        bottomLeft.setEnabled(false);
                        bottomRight.setEnabled(false);

                        gameTimerText.setText("00:000");
                        gameTimerText.setTextColor(Color.RED);
                        final TextView loseText = (TextView) findViewById(R.id.loseText);
                        loseText.setBackgroundColor(Color.LTGRAY);
                        loseText.setTextColor(Color.BLACK);
                        loseText.setText("TIME'S UP");

                        Resources r = getResources();
                        int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                        loseText.setTextSize(loseTextPx);

                        new CountDownTimer(2000, 1000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                final String score = String.valueOf(counter);
                                String endScreenLoseText = loseText.getText().toString();

                                Intent i = new Intent(PlayScreen.this, EndScreen.class);
                                i.putExtra("score", score);
                                i.putExtra("endScreenLoseText", endScreenLoseText);
                                i.putExtra("modeString", mode);
                                startActivity(i);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }


                        }.start();
                    }


                }.start();
            }
        } else {
            //TextView loseText = (TextView)findViewById(R.id.loseText);
            if (!mode.equals("zen")) {
                loseText.setText("GAME OVER");
                loseText.setTextColor(Color.BLACK);
                loseText.setBackgroundColor(Color.LTGRAY);
                topLeft.setEnabled(false);
                topRight.setEnabled(false);
                bottomLeft.setEnabled(false);
                bottomRight.setEnabled(false);

                Resources r = getResources();
                int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                loseText.setTextSize(loseTextPx);

                countDownTimer.cancel();

                final String score = String.valueOf(counter);
                final String endScreenLoseText = loseText.getText().toString();

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        Intent i = new Intent(PlayScreen.this, EndScreen.class);
                        i.putExtra("score", score);
                        i.putExtra("endScreenLoseText", endScreenLoseText);
                        i.putExtra("modeString", mode);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }


                }.start();
            }else{
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        gameTimerText.setTextColor(Color.RED);
                        gameTimerText.setText("Try Again");
                    }

                    public void onFinish(){
                        gameTimerText.setTextColor(Color.WHITE);
                    }
                }.start();
            }
        }
    }

    public void onClickBottomLeft(View view) {
        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
        ImageButton masterButton = (ImageButton) findViewById(R.id.masterButton);
        TextView loseText = (TextView) findViewById(R.id.loseText);

        ColorDrawable buttonColor = (ColorDrawable) bottomLeft.getBackground();
        Integer buttonColorId = buttonColor.getColor();
        ColorDrawable masterColor = (ColorDrawable) masterButton.getBackground();
        Integer masterColorId = masterColor.getColor();

        TextView counterText = (TextView) findViewById(R.id.counterText);

        if (buttonColorId.equals(masterColorId)) {
            nextLevel();
            counter++;
            counterText.setText(String.valueOf(counter));

            TextView secondsText = (TextView) findViewById(R.id.secondsText);
            if (mode.equals("classic")) {
                countDownTimer.cancel();
                if (counter <= 50 && counter % 10 == 0) {
                    seconds = seconds - 200;
                    secondsText.setText(seconds.toString() + " ms");
                }
                countDownTimer = new CountDownTimer(seconds, 50) {

                    public void onTick(long millisUntilFinished) {

                        if (mode.equals("timed")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) < 5) {
                                gameTimerText.setTextColor(0xffff4500);
                            }
                        } else if (mode.equals("classic")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == 1) {
                                gameTimerText.setTextColor(Color.GREEN);
                            } else if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) < 500) {
                                gameTimerText.setTextColor(Color.RED);
                            } else {
                                gameTimerText.setTextColor(0xffffa500);
                            }
                        }

                        if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) >= 100) {
                            gameTimerText.setText("" + String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        } else {
                            gameTimerText.setText("" + String.format(FORMAT1,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        }
                    }

                    public void onFinish() {
                        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
                        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
                        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
                        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
                        topLeft.setEnabled(false);
                        topRight.setEnabled(false);
                        bottomLeft.setEnabled(false);
                        bottomRight.setEnabled(false);

                        gameTimerText.setText("00:000");
                        gameTimerText.setTextColor(Color.RED);
                        final TextView loseText = (TextView) findViewById(R.id.loseText);
                        loseText.setBackgroundColor(Color.LTGRAY);
                        loseText.setTextColor(Color.BLACK);
                        loseText.setText("TIME'S UP");

                        Resources r = getResources();
                        int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                        loseText.setTextSize(loseTextPx);

                        new CountDownTimer(2000, 1000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                final String score = String.valueOf(counter);
                                String endScreenLoseText = loseText.getText().toString();

                                Intent i = new Intent(PlayScreen.this, EndScreen.class);
                                i.putExtra("score", score);
                                i.putExtra("endScreenLoseText", endScreenLoseText);
                                i.putExtra("modeString", mode);
                                startActivity(i);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }


                        }.start();
                    }


                }.start();
            }
        } else {
            //TextView loseText = (TextView)findViewById(R.id.loseText);
            if (!mode.equals("zen")) {
                loseText.setText("GAME OVER");
                loseText.setTextColor(Color.BLACK);
                loseText.setBackgroundColor(Color.LTGRAY);
                topLeft.setEnabled(false);
                topRight.setEnabled(false);
                bottomLeft.setEnabled(false);
                bottomRight.setEnabled(false);

                Resources r = getResources();
                int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                loseText.setTextSize(loseTextPx);

                countDownTimer.cancel();

                final String score = String.valueOf(counter);
                final String endScreenLoseText = loseText.getText().toString();

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        Intent i = new Intent(PlayScreen.this, EndScreen.class);
                        i.putExtra("score", score);
                        i.putExtra("endScreenLoseText", endScreenLoseText);
                        i.putExtra("modeString", mode);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }


                }.start();
            }else{
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        gameTimerText.setTextColor(Color.RED);
                        gameTimerText.setText("Try Again");
                    }

                    public void onFinish(){
                        gameTimerText.setTextColor(Color.WHITE);
                    }
                }.start();
            }
        }
    }

    public void onClickBottomRight(View view) {
        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
        ImageButton masterButton = (ImageButton) findViewById(R.id.masterButton);
        TextView loseText = (TextView) findViewById(R.id.loseText);

        ColorDrawable buttonColor = (ColorDrawable) bottomRight.getBackground();
        Integer buttonColorId = buttonColor.getColor();
        ColorDrawable masterColor = (ColorDrawable) masterButton.getBackground();
        Integer masterColorId = masterColor.getColor();

        TextView counterText = (TextView) findViewById(R.id.counterText);

        if (buttonColorId.equals(masterColorId)) {
            nextLevel();
            counter++;
            counterText.setText(String.valueOf(counter));

            TextView secondsText = (TextView) findViewById(R.id.secondsText);
            if (mode.equals("classic")) {
                countDownTimer.cancel();
                if (counter <= 50 && counter % 10 == 0) {
                    seconds = seconds - 200;
                    secondsText.setText(seconds.toString() + " ms");
                }
                countDownTimer = new CountDownTimer(seconds, 50) {

                    public void onTick(long millisUntilFinished) {

                        if (mode.equals("timed")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) < 5) {
                                gameTimerText.setTextColor(0xffff4500);
                            }
                        } else if (mode.equals("classic")) {
                            if (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == 1) {
                                gameTimerText.setTextColor(Color.GREEN);
                            } else if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) < 500) {
                                gameTimerText.setTextColor(Color.RED);
                            } else {
                                gameTimerText.setTextColor(0xffffa500);
                            }
                        }

                        if (TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)) >= 100) {
                            gameTimerText.setText("" + String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        } else {
                            gameTimerText.setText("" + String.format(FORMAT1,

                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) - TimeUnit.SECONDS.toMillis(
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                    )));
                        }
                    }

                    public void onFinish() {
                        ImageButton topLeft = (ImageButton) findViewById(R.id.topLeft);
                        ImageButton topRight = (ImageButton) findViewById(R.id.topRight);
                        ImageButton bottomLeft = (ImageButton) findViewById(R.id.bottomLeft);
                        ImageButton bottomRight = (ImageButton) findViewById(R.id.bottomRight);
                        topLeft.setEnabled(false);
                        topRight.setEnabled(false);
                        bottomLeft.setEnabled(false);
                        bottomRight.setEnabled(false);

                        gameTimerText.setText("00:000");
                        gameTimerText.setTextColor(Color.RED);
                        final TextView loseText = (TextView) findViewById(R.id.loseText);
                        loseText.setBackgroundColor(Color.LTGRAY);
                        loseText.setTextColor(Color.BLACK);
                        loseText.setText("TIME'S UP");

                        Resources r = getResources();
                        int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                        loseText.setTextSize(loseTextPx);

                        new CountDownTimer(2000, 1000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                final String score = String.valueOf(counter);
                                String endScreenLoseText = loseText.getText().toString();

                                Intent i = new Intent(PlayScreen.this, EndScreen.class);
                                i.putExtra("score", score);
                                i.putExtra("endScreenLoseText", endScreenLoseText);
                                i.putExtra("modeString", mode);
                                startActivity(i);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }


                        }.start();
                    }


                }.start();
            }
        } else {
            //TextView loseText = (TextView)findViewById(R.id.loseText);
            if (!mode.equals("zen")) {
                loseText.setText("GAME OVER");
                loseText.setTextColor(Color.BLACK);
                loseText.setBackgroundColor(Color.LTGRAY);
                topLeft.setEnabled(false);
                topRight.setEnabled(false);
                bottomLeft.setEnabled(false);
                bottomRight.setEnabled(false);

                Resources r = getResources();
                int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, r.getDisplayMetrics());
                loseText.setTextSize(loseTextPx);

                countDownTimer.cancel();

                final String score = String.valueOf(counter);
                final String endScreenLoseText = loseText.getText().toString();

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        Intent i = new Intent(PlayScreen.this, EndScreen.class);
                        i.putExtra("score", score);
                        i.putExtra("endScreenLoseText", endScreenLoseText);
                        i.putExtra("modeString", mode);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }


                }.start();
            }else{
                new CountDownTimer(1000, 1000){
                    public void onTick(long millisUntilFinished){
                        gameTimerText.setTextColor(Color.RED);
                        gameTimerText.setText("Try Again");
                    }

                    public void onFinish(){
                        gameTimerText.setTextColor(Color.WHITE);
                    }
                }.start();
            }
        }
    }
}
