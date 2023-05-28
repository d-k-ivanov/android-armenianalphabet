package com.pupupon.armenianalphabet;

import static com.pupupon.armenianalphabet.Tools.RAW;
import static com.pupupon.armenianalphabet.Tools.STRING;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_LISTEN;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_NEXT;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_PREVIOUS;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.CATEGORY_SOUND_EVENTS;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsActivity;

public class LearnActivity extends GoogleAnalyticsActivity implements OnClickListener {
    private final Button[] buttons = new Button[3];
    private final String[] letters = new String[39];
    int globalPosition = 0;
    // Vars:
    private TextView upperCaseText;
    private TextView lowerCaseText;
    private TextView soundText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_activity);
        Typeface mainFont = Tools.setFont(this);
        upperCaseText = findViewById(R.id.alphabetUpperCase);
        upperCaseText.setTypeface(mainFont);
        lowerCaseText = findViewById(R.id.alphabetLowerCase);
        lowerCaseText.setTypeface(mainFont);
        soundText = findViewById(R.id.alphabetIPA);
        soundText.setTypeface(mainFont);
        setLetterArrayValue();
        buttons[0] = findViewById(R.id.alphabetListen);
        buttons[1] = findViewById(R.id.alphabetPrevious);
        buttons[2] = findViewById(R.id.alphabetNext);
        for (Button i : buttons) {
            i.setOnClickListener(this);
            i.setTypeface(mainFont);
        }
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.alphabetListen:
                listen();
                userAction(ACTION_LISTEN);
                break;
            case R.id.alphabetPrevious:
                previousLetter();
                setup();
                listen();
                userAction(ACTION_PREVIOUS);
                break;
            case R.id.alphabetNext:
                nextLetter();
                setup();
                listen();
                userAction(ACTION_NEXT);
                break;
        }
    }

    private void setup() {
        if (this.getSupportActionBar() != null) {
            String armPrefix = (Storage.getEasternArmenian() ? getString(R.string.eastern) : getString(R.string.western)) + " ";
            this.getSupportActionBar().setTitle(armPrefix + getString(R.string.app_name) + ": " + (globalPosition + 1) + " of 39");
        }
        String[] letter = getLetters(letters[globalPosition]);
        upperCaseText.setText(letter[0]);
        lowerCaseText.setText(letter[1]);
        soundText.setText(letter[2]);
    }

    private String[] getLetters(String letter) {
        return ((String) getResources().getText(getResources()
            .getIdentifier(letter, STRING, getPackageName()))).split(";");
    }

    private void setLetterArrayValue() {
        for (int i = 1; i <= 39; i++) {
            letters[i - 1] = Tools.getFileResourceName() + i;
        }
        setup();
    }

    private void listen() {
        final View view = buttons[0];
        view.setBackgroundResource(R.drawable.button_green);
        view.setEnabled(false);
        String letter = letters[globalPosition];
        setEvent(CATEGORY_SOUND_EVENTS, ACTION_LISTEN, getLetters(letter)[0]);
        Tools.playSound(this, getResources().getIdentifier(letter, RAW, getPackageName()));
        // Execute some code after 2 seconds have passed
        Handler handler1 = new Handler(Looper.getMainLooper());
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.button);
                view.setEnabled(true);
            }
        }, 1000);
    }

    private void previousLetter() {
        globalPosition = globalPosition == 0 ? 38 : globalPosition - 1;
    }

    private void nextLetter() {
        globalPosition = globalPosition == 38 ? 0 : globalPosition + 1;
    }
}
