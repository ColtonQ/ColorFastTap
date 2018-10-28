package com.notexample.coltonquan.colorblock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Switch;

public class MenuScreen extends AppCompatActivity {

    String mode;
    public static final String prefsName = "myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        //Setting widths and heights

        Resources r = getResources();

        ImageView homeImage = (ImageView)findViewById(R.id.homeImage);
        final ImageView classicImage = (ImageView)findViewById(R.id.classicImage);
        final ImageView timedImage = (ImageView)findViewById(R.id.timedImage);
        final ImageView zenImage = (ImageView)findViewById(R.id.zenImage);

        int homeImagePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,r.getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(homeImagePx, homeImagePx);
        homeImage.setLayoutParams(layoutParams);
        int colorGrey = Color.parseColor("#CECECE");
        homeImage.setColorFilter(colorGrey);

        Button menuPlayButton = (Button) findViewById(R.id.menuPlayButton);
        menuPlayButton.setEnabled(false);
        menuPlayButton.setTextColor(Color.WHITE);
        menuPlayButton.setBackgroundColor(Color.WHITE);

        Switch colorblindSwitch = (Switch) findViewById(R.id.colorblindSwitch);

        final SharedPreferences prefs = getSharedPreferences(prefsName, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("colorblindString");
        editor.putBoolean("colorblindString", false);
        editor.apply();

        colorblindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.setSaturation(0);

                    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                    classicImage.setColorFilter(filter);
                    timedImage.setColorFilter(filter);
                    zenImage.setColorFilter(filter);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.remove("colorblindString");
                    editor.putBoolean("colorblindString", true);
                    editor.apply();
                }else{
                    classicImage.clearColorFilter();
                    timedImage.clearColorFilter();
                    zenImage.clearColorFilter();

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.remove("colorblindString");
                    editor.putBoolean("colorblindString", false);
                    editor.apply();
                }
            }
        });
    }

    public void onClickHomeImage(View view){
        Intent iHome = new Intent(MenuScreen.this, HomeScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClick(View view){
            Intent i = new Intent(MenuScreen.this, PlayScreen.class);
            i.putExtra("modeString", mode);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClickClassic(View view){
        TextView modeTitleText = (TextView)findViewById(R.id.modeTitleText);
        TextView descriptionText = (TextView)findViewById(R.id.descriptionText);
        modeTitleText.setText("CLASSIC");
        descriptionText.setText("The time allotted to match each color decreases as the game progresses.");
        descriptionText.setTextColor(Color.BLACK);

        Button menuPlayButton = (Button) findViewById(R.id.menuPlayButton);
        menuPlayButton.setEnabled(true);
        menuPlayButton.setTextColor(Color.BLACK);
        menuPlayButton.setBackgroundColor(0x4cbdbdbd);

        mode = "classic";
    }

    public void onClickTimed(View view){
        TextView modeTitleText = (TextView)findViewById(R.id.modeTitleText);
        TextView descriptionText = (TextView)findViewById(R.id.descriptionText);
        modeTitleText.setText("COUNTDOWN");
        descriptionText.setText("Match as many colors as you can in 20 seconds.");
        descriptionText.setTextColor(Color.BLACK);

        Button menuPlayButton = (Button) findViewById(R.id.menuPlayButton);
        menuPlayButton.setEnabled(true);
        menuPlayButton.setTextColor(Color.BLACK);
        menuPlayButton.setBackgroundColor(0x4cbdbdbd);

        mode = "timed";
    }

    public void onClickZen(View view){
        TextView modeTitleText = (TextView)findViewById(R.id.modeTitleText);
        TextView descriptionText = (TextView)findViewById(R.id.descriptionText);
        modeTitleText.setText("ZEN");
        descriptionText.setText("Practice your color-matching skills in our stress-free Zen environment.");
        descriptionText.setTextColor(Color.BLACK);

        Button menuPlayButton = (Button) findViewById(R.id.menuPlayButton);
        menuPlayButton.setEnabled(true);
        menuPlayButton.setTextColor(Color.BLACK);
        menuPlayButton.setBackgroundColor(0x4cbdbdbd);

        mode = "zen";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
