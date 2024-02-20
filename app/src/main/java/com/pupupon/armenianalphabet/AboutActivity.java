package com.pupupon.armenianalphabet;

import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_GITHUB;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsActivity;
import com.pupupon.armenianalphabet.utils.DefensiveURLSpan;

public class AboutActivity extends GoogleAnalyticsActivity {
    DefensiveURLSpan.OnUrlListener mUrlListener = url -> userAction(ACTION_GITHUB, url);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        Typeface mainFont = Tools.setFont(this);

        TextView aboutText = findViewById(R.id.aboutText);
        aboutText.setTypeface(mainFont);

        TextView aboutCopyright = findViewById(R.id.aboutCopyright);
        aboutCopyright.setTypeface(mainFont);
        DefensiveURLSpan.setUrlClickListener(aboutCopyright, mUrlListener);
    }
}
