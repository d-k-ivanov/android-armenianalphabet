package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AlphabetActivity extends AppCompatActivity implements OnClickListener {
    int globalPosition;
    boolean easternArmenian = true;
    // Vars:
    private TextView upperCaseText;
    private TextView lowerCaseText;
    private TextView soundText;
    private Button[] buttons = new Button[3];
    private String[] letters = new String[39];
    private Menu mMenu;
    private TextView alphabetTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Typeface mainFont = Tools.setFont(this);

        upperCaseText = findViewById(R.id.alphabetUpperCase);
        upperCaseText.setTypeface(mainFont);
        lowerCaseText = findViewById(R.id.alphabetLowerCase);
        lowerCaseText.setTypeface(mainFont);
        soundText = findViewById(R.id.alphabetIPA);
        soundText.setTypeface(mainFont);

        globalPosition = 0;
        setLetterArrayValue();

        buttons[0] = findViewById(R.id.alphabetListen);
        buttons[1] = findViewById(R.id.alphabetPrevious);
        buttons[2] = findViewById(R.id.alphabetNext);

        for (Button i : buttons) {
            i.setOnClickListener(this);
            i.setTypeface(mainFont);
        }
    }

    @Override
    public void onClick(final View view) {

        switch (view.getId()) {
            case R.id.alphabetListen:
                view.setBackgroundResource(R.drawable.button_green);
                view.setEnabled(false);

                Tools.playSound(this, getResources().getIdentifier(letters[globalPosition], "raw", "com.pupupon.armenianalphabet"));
                //Tools.playSoundFromAsset(this, "audio/" + letters[globalPosition] + ".ogg");

                // Execute some code after 2 seconds have passed
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setBackgroundResource(R.drawable.button);
                        view.setEnabled(true);
                    }
                }, 1000);

                break;
            case R.id.alphabetPrevious:
                if (globalPosition == 0)
                    globalPosition = 38;
                else
                    globalPosition--;
                setup();
                break;
            case R.id.alphabetNext:
                if (globalPosition == 38)
                    globalPosition = 0;
                else
                    globalPosition++;
                setup();
                break;
        }

    }

    private void setup(){
        String armPrefix = (Storage.getEasternArmenian() ? getString(R.string.eastern) : getString(R.string.western)) + " ";
        this.getSupportActionBar().setTitle(armPrefix + getString(R.string.app_name) + ": " + (globalPosition+1)+ " of 39");
        String[] letter = ((String) getResources().getText(getResources().getIdentifier(letters[globalPosition], "string", "com.pupupon.armenianalphabet"))).split(";");
        upperCaseText.setText(letter[0]);
        lowerCaseText.setText(letter[1]);
        soundText.setText(letter[2]);
    }

    private void setLetterArrayValue() {
        for (int i = 1; i <= 39; i++) {
            letters[i - 1] = Tools.getFileResourceName() + i;
        }
        setup();
    }
}
