package com.notexample.coltonquan.colorblock;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.Resources;
import android.util.TypedValue;


public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Setting widths and heights
        Resources r = getResources();

        TextView colorBlockTitle = (TextView)findViewById(R.id.colorblockTitle);

        Button topLeft = (Button)findViewById(R.id.topLeft);
        Button topRight = (Button)findViewById(R.id.topRight);
        Button bottomLeft = (Button)findViewById(R.id.bottomLeft);
        Button bottomRight = (Button)findViewById(R.id.bottomRight);

        ImageView settingsImage = (ImageView)findViewById(R.id.settingsImage);

        ImageView paletteImage = (ImageView)findViewById(R.id.paletteImage);
        int colorGrey = Color.parseColor("#CECECE");
        paletteImage.setColorFilter(colorGrey);

        Button playButton = (Button)findViewById(R.id.playButton);

        int settingsImagePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,40,r.getDisplayMetrics());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(settingsImagePx, settingsImagePx);
        settingsImage.setLayoutParams(layoutParams);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)settingsImage.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        settingsImage.setLayoutParams(params);

        //int colorGrey = Color.parseColor("#CECECE");
        //settingsImage.setColorFilter(colorGrey);


        int colorBlockTitlePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,18,r.getDisplayMetrics());
        colorBlockTitle.setTextSize(colorBlockTitlePx);

        int topLeftPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,r.getDisplayMetrics());
        int topRightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,r.getDisplayMetrics());
        int bottomLeftPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,r.getDisplayMetrics());
        int bottomRightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,r.getDisplayMetrics());
        topLeft.setHeight(topLeftPx);
        topLeft.setWidth(topLeftPx);
        topRight.setHeight(topRightPx);
        topRight.setWidth(topRightPx);
        bottomLeft.setHeight(bottomLeftPx);
        bottomLeft.setWidth(bottomLeftPx);
        bottomRight.setHeight(bottomRightPx);
        bottomRight.setWidth(bottomRightPx);

        int playButtonWidthPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75,r.getDisplayMetrics());
        int playButtonHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,r.getDisplayMetrics());

        playButton.setWidth(playButtonWidthPx);
        playButton.setHeight(playButtonHeightPx);

        topLeft.setBackgroundColor(Color.RED);
        topRight.setBackgroundColor(Color.BLUE);
        bottomLeft.setBackgroundColor(Color.GREEN);
        bottomRight.setBackgroundColor(Color.MAGENTA);

    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onBackPressed(); //TAKE THIS OUT LATER
    }

    public void onClickPaletteImage(View view){
        /*Intent iStore = new Intent(HomeScreen.this, StoreScreen.class);
        startActivity(iStore);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);*/

    }

    public void onClickSettings(View v){
        Intent iSettings = new Intent(HomeScreen.this, SettingsScreen.class);
        startActivity(iSettings);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onClick(View view) {
        Intent i = new Intent(HomeScreen.this, MenuScreen.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}


