package com.notexample.coltonquan.colorblock;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Settings numeric values
        Resources r = getResources();

        TextView aboutTitleText = (TextView)findViewById(R.id.aboutTitleText);
        TextView aboutMeText = (TextView)findViewById(R.id.aboutMeText);
        ImageView homeImage = (ImageView)findViewById(R.id.homeImage);

        int aboutTitleTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,10,r.getDisplayMetrics());
        aboutTitleText.setTextSize(aboutTitleTextPx);

        int aboutMeTextPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,8,r.getDisplayMetrics());
        aboutMeText.setTextSize(aboutMeTextPx);

        int homeImagePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,r.getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(homeImagePx, homeImagePx);
        homeImage.setLayoutParams(layoutParams);
        int colorGrey = Color.parseColor("#CECECE");
        homeImage.setColorFilter(colorGrey);

        //Setting aboutMeText

    }

    public void onClickHomeImage(View view){
        Intent iHome = new Intent(AboutScreen.this, HomeScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
