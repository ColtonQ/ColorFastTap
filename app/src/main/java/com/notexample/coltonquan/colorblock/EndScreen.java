package com.notexample.coltonquan.colorblock;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
/*import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;*/

import org.w3c.dom.Text;


public class EndScreen extends AppCompatActivity {

    public static final String prefsName = "myPrefsFile";
    public static Integer highScoreInt, colorcoin;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       /* MobileAds.initialize(getApplicationContext(), "ca-app-pub-4688351229631812~4493597882");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        //Setting widths and heights

        TextView loseText = (TextView) findViewById(R.id.loseText);
        TextView scoreText = (TextView) findViewById(R.id.scoreText);
        TextView newHighScoreText = (TextView) findViewById(R.id.newHighscoreText);
        TextView highScoreText = (TextView) findViewById(R.id.highScoreText);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        ImageView homeImage = (ImageView) findViewById(R.id.homeImage);

        Resources r = getResources();

        int highScoreTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,10,r.getDisplayMetrics());
        highScoreText.setTextSize(highScoreTextPx);

        int newHighScoreTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, r.getDisplayMetrics());
        newHighScoreText.setTextSize(newHighScoreTextPx);

        int loseTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 17, r.getDisplayMetrics());
        loseText.setTextSize(loseTextPx);

        int scoreTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 50, r.getDisplayMetrics());
        scoreText.setTextSize(scoreTextPx);

        int playAgainButtonWidthPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, r.getDisplayMetrics());
        int playAgainButtonHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
        playAgainButton.setWidth(playAgainButtonWidthPx);
        playAgainButton.setHeight(playAgainButtonHeightPx);

        int homeImagePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,r.getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(homeImagePx, homeImagePx);
        homeImage.setLayoutParams(layoutParams);
        int colorGrey = Color.parseColor("#CECECE");
        homeImage.setColorFilter(colorGrey);

        Bundle scoreData = getIntent().getExtras();
        if (scoreData == null) {
            return;
        }
        String score = scoreData.getString("score");

        scoreText.setText(score);

        String endScreenLoseText = scoreData.getString("endScreenLoseText");

        loseText.setText(endScreenLoseText);

        mode = scoreData.getString("modeString");


        SharedPreferences prefs = getSharedPreferences(prefsName, MODE_PRIVATE);

        colorcoin = prefs.getInt("colorcoinString", 0);
        Integer scoreInt = Integer.parseInt(score);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

        TextView colorcoinText = (TextView)findViewById(R.id.colorcoinText);
        TextView colorcoinAddText = (TextView)findViewById(R.id.colorcoinAddText);

        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("colorcoinString");
        editor.putInt("colorcoinString", colorcoin+Integer.parseInt(score));
        editor.apply();

        colorcoin = prefs.getInt("colorcoinString", 0);

        colorcoinText.setText(String.valueOf(colorcoin));
        colorcoinAddText.setText("+" + score);

        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
        colorcoinAddText.startAnimation(fadeIn);
        fadeOut.setDuration(1200);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(2000+fadeIn.getStartOffset());
        colorcoinAddText.startAnimation(fadeOut);


        if (mode.equals("classic")) {
            highScoreInt = prefs.getInt("highScoreStringClassic", 0);
        }else{
            highScoreInt = prefs.getInt("highScoreStringTimed", 0);
        }

        //Integer scoreInt = Integer.parseInt(score);

        if (scoreInt > highScoreInt) {
            newHighScoreText.setTextColor(Color.BLACK);

            //SharedPreferences.Editor editor = prefs.edit();
            if (mode.equals("classic")) {
                editor.remove("highScoreStringClassic");
                editor.putInt("highScoreStringClassic", scoreInt);
                editor.apply();

                highScoreText.setText("Classic Highscore: "+scoreInt.toString());
            }else{
                editor.remove("highScoreStringTimed");
                editor.putInt("highScoreStringTimed", scoreInt);
                editor.apply();

                highScoreText.setText("Countdown Highscore: "+scoreInt.toString());
            }


        }
        else {
            newHighScoreText.setTextColor(Color.WHITE);
            if (mode.equals("classic")) {
                highScoreText.setText("Classic Highscore: " + highScoreInt.toString());
            }else{
                highScoreText.setText("Countdown Highscore: " + highScoreInt.toString());
            }
    }




    }

    @Override
    public void onBackPressed() {
        Intent iHome = new Intent(EndScreen.this, MenuScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClick(View view){
        Bundle scoreData = getIntent().getExtras();
        if (scoreData == null) {
            return;
        }
        mode = scoreData.getString("modeString");

        Intent i = new Intent(EndScreen.this, PlayScreen.class);
        i.putExtra("modeString", mode);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClickHomeImage(View view){
        Intent iHome = new Intent(EndScreen.this, HomeScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
