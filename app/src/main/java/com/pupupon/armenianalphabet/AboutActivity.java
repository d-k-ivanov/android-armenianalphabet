package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Typeface droidSansArmenian = Typeface.createFromAsset(getAssets(), "fonts/DroidSansArmenian.ttf");
        Typeface sylfaen = Typeface.createFromAsset(getAssets(), "fonts/Sylfaen.ttf");
        Typeface mainFont = sylfaen;

        TextView aboutText = (TextView) findViewById(R.id.aboutText);
        TextView aboutCopyright = (TextView) findViewById(R.id.aboutCopyright);
        aboutText.setTypeface(mainFont);
        aboutCopyright.setTypeface(mainFont);


    }
}
