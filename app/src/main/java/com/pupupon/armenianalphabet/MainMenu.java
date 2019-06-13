package com.pupupon.armenianalphabet;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsActivity;
import com.pupupon.armenianalphabet.utils.DefensiveURLSpan;

import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_ABOUT;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_ALPHABET;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_GITHUB;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_QUIZ;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_SWITCH_PRONUNCIATION;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.LABEL_EASTERN;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.LABEL_WESTERN;


public class MainMenu extends GoogleAnalyticsActivity {
    // Vars:
    Button[] buttons = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Typeface mainFont = Tools.setFont(this);

        // Answer Section
        buttons[0] = findViewById(R.id.menuEntry1);
        buttons[1] = findViewById(R.id.menuEntry2);
        buttons[2] = findViewById(R.id.menuEntry3);
        /*buttons[2] = (Button) findViewById(R.id.menuEntry4);*/
        buttons[3] = findViewById(R.id.menuEntry5);
        DefensiveURLSpan.setUrlClickListener((TextView) findViewById(R.id.aboutCopyright),
            new DefensiveURLSpan.OnUrlListener() {
                @Override
                public void onClick(String url) {
                    userAction(ACTION_GITHUB, url);
                }
            });
        // Init Buttons:
        for (Button i : buttons) {
            i.setTypeface(mainFont);
        }

        Storage.init(getApplicationContext());
        buttons[2].setText(getPronunciationIndication());
        setTittle();

    }

    public void startMenuEntry1(View view) {
        userAction(ACTION_ALPHABET);
        Intent alphabet = new Intent(this, AlphabetActivity.class);
        startActivity(alphabet);
    }

    public void startMenuEntry2(View view) {
        userAction(ACTION_QUIZ);
        Intent quiz = new Intent(this, QuizActivity.class);
        startActivity(quiz);
    }

    public void startMenuEntry3(View view) {
        eventSwitchPronunciation();
        Snackbar.make(findViewById(R.id.activity_main_menu),
            getString(getArmenianIndication()) + getString(R.string.enabled),
            Snackbar.LENGTH_SHORT).show();
        Storage.setEasternArmenian(!Storage.getEasternArmenian());
        buttons[2].setText(getPronunciationIndication());
        setTittle();

    }

    public void startMenuEntry5(View view) {
        userAction(ACTION_ABOUT);
        Intent about = new Intent(this, AboutActivity.class);
        startActivity(about);
    }

    private int getPronunciationIndication() {
        return Storage.getEasternArmenian() ? R.string.westernPronunciation : R.string.easternPronunciation;
    }

    private void eventSwitchPronunciation() {
        if(Storage.getEasternArmenian()) {
            userAction(ACTION_SWITCH_PRONUNCIATION, LABEL_WESTERN);
        } else {
            userAction(ACTION_SWITCH_PRONUNCIATION, LABEL_EASTERN);
        }
    }

    private int getArmenianIndication() {
        return Storage.getEasternArmenian() ? R.string.western : R.string.eastern;
    }

    private void setTittle() {
        String armPrefix = (Storage.getEasternArmenian() ? getString(R.string.eastern) : getString(R.string.western)) + " ";
        this.getSupportActionBar().setTitle(armPrefix + getString(R.string.app_name));
    }
}
