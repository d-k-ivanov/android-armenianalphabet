package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    Typeface droidSansArmenian;
    Typeface sylfaen;
    Typeface mainFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        droidSansArmenian = Typeface.createFromAsset(getAssets(), "fonts/DroidSansArmenian.ttf");
        sylfaen = Typeface.createFromAsset(getAssets(), "fonts/Sylfaen.ttf");
        mainFont = droidSansArmenian;

        // Answer Section
        TextView aboutText = (TextView) findViewById(R.id.aboutText);
        TextView aboutCopyright = (TextView) findViewById(R.id.aboutCopyright);
        aboutText.setTypeface(mainFont);
        aboutCopyright.setTypeface(mainFont);


    }
}
