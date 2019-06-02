package com.pupupon.armenianalphabet;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


public class MainMenu extends AppCompatActivity {
    // Vars:
    Button[] buttons = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Typeface mainFont = Tools.setFont(this);

        // Answer Section
        buttons[0] = (Button) findViewById(R.id.menuEntry1);
        buttons[1] = (Button) findViewById(R.id.menuEntry2);
        buttons[2] = (Button) findViewById(R.id.menuEntry3);
        /*buttons[2] = (Button) findViewById(R.id.menuEntry4);*/
        buttons[3] = (Button) findViewById(R.id.menuEntry5);

        // Init Buttons:
        for (Button i : buttons) {
            i.setTypeface(mainFont);
        }

        Storage.init(getApplicationContext());
        buttons[2].setText(getPronunciationIndication());

    }

    public void startMenuEntry1(View view) {
        Intent alphabet = new Intent(this, AlphabetActivity.class);
        startActivity(alphabet);
    }

    public void startMenuEntry2(View view) {
        Intent quiz = new Intent(this, QuizActivity.class);
        startActivity(quiz);
    }

    public void startMenuEntry3(View view) {
        Snackbar.make(findViewById(R.id.activity_main_menu),
            getString(getPronunciationIndication()) + getString(R.string.enabled),
            Snackbar.LENGTH_SHORT).show();
        Storage.setEasternArmenian(!Storage.getEasternArmenian());
        buttons[2].setText(getPronunciationIndication());
    }

    /*public void startMenuEntry4(View view) {
        Intent help = new Intent(this, HelpActivity.class);
        startActivity(help);
    }*/

    public void startMenuEntry5(View view) {
        Intent about = new Intent(this, AboutActivity.class);
        startActivity(about);
    }

    private int getPronunciationIndication() {
        return Storage.getEasternArmenian() ? R.string.westernPronunciation : R.string.easternPronunciation;
    }
}
