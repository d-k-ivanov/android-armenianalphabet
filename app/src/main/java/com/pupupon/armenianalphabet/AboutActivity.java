package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsActivity;
import com.pupupon.armenianalphabet.utils.DefensiveURLSpan;

import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_GITHUB;

public class AboutActivity extends GoogleAnalyticsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        Typeface mainFont = Tools.setFont(this);

        TextView aboutText = findViewById(R.id.aboutText);
        aboutText.setTypeface(mainFont);
        DefensiveURLSpan.setUrlClickListener(aboutText, mUrlListener);

//        TextView aboutCopyright = findViewById(R.id.aboutCopyright);
//        aboutCopyright.setTypeface(mainFont);
//        DefensiveURLSpan.setUrlClickListener(aboutCopyright, mUrlListener);
    }

    DefensiveURLSpan.OnUrlListener mUrlListener = new DefensiveURLSpan.OnUrlListener() {
        @Override
        public void onClick(String url) {
            userAction(ACTION_GITHUB, url);
        }
    };
}
