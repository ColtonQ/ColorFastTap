package com.notexample.coltonquan.colorblock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoreScreen extends AppCompatActivity {

    public static final String prefsName = "myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_screen);

        SharedPreferences prefs = getSharedPreferences(prefsName, MODE_PRIVATE);

        Integer colorcoin = prefs.getInt("colorcoinString", 0);

        //TextView colorcoinCountText = (TextView)findViewById(R.id.colorcoinCountText);
        //colorcoinCountText.setText("Your Color Coins: " + String.valueOf(colorcoin));

        Resources r = getResources();

        ImageView homeImage = (ImageView)findViewById(R.id.homeImage);

        int homeImagePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,r.getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(homeImagePx, homeImagePx);
        homeImage.setLayoutParams(layoutParams);
        int colorGrey = Color.parseColor("#CECECE");
        homeImage.setColorFilter(colorGrey);

    }

    public void onClickHomeImage(View view){
        Intent iHome = new Intent(StoreScreen.this, HomeScreen.class);
        startActivity(iHome);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
