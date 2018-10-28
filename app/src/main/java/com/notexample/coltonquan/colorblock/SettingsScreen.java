package com.notexample.coltonquan.colorblock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

public class SettingsScreen extends AppCompatActivity {

    public static final String prefsName = "myPrefsFile";
    public static Integer highScoreInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView settingsTitleText = (TextView)findViewById(R.id.settingsTitleText);
        Button clearDataButton = (Button)findViewById(R.id.clearDataButton);
        ImageView homeImage = (ImageView)findViewById(R.id.homeImage);

        Resources r = getResources();

        //Setting numeric values
        int settingsTitleTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,10,r.getDisplayMetrics());
        settingsTitleText.setTextSize(settingsTitleTextPx);

        int clearDataButtonWidthPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
        clearDataButton.setWidth(clearDataButtonWidthPx);
        int clearDataButtonHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,r.getDisplayMetrics());
        clearDataButton.setHeight(clearDataButtonHeightPx);

        int homeImagePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,r.getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(homeImagePx, homeImagePx);
        homeImage.setLayoutParams(layoutParams);
        int colorGrey = Color.parseColor("#CECECE");
        homeImage.setColorFilter(colorGrey);

    }

    public void onClickClearData(View view){

        Button clearDataButton = (Button)findViewById(R.id.clearDataButton);

        if (clearDataButton.getText().equals("CLEAR USER DATA")) {
            clearDataButton.setText("ARE YOU SURE?");
        }else if (clearDataButton.getText().equals("ARE YOU SURE?")) {

            SharedPreferences prefs = getSharedPreferences(prefsName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.remove("highScoreStringClassic");
            editor.remove("highScoreStringTimed");
            editor.remove("colorcoinString");
            editor.putInt("highScoreStringClassic", 0);
            editor.putInt("highScoreStringTimed", 0);
            editor.putInt("colorcoinString", 0);
            editor.apply();

            //highScoreInt = prefs.getInt("highScoreString", 0);

            clearDataButton.setText("USER DATA CLEARED");
        }

    }

    public void onClickHomeImage(View view){
        Intent iHome = new Intent(SettingsScreen.this, HomeScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClickAbout(View view){
        Intent iAbout = new Intent(SettingsScreen.this, AboutScreen.class);
        startActivity(iAbout);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
