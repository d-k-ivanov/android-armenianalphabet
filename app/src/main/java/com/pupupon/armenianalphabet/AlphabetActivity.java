package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.pupupon.armenianalphabet.Tools.STRING;

public class AlphabetActivity extends AppCompatActivity {
    private int BUTTONS = 39;
    private Button[] buttons = new Button[BUTTONS];
//    private String[] letters = new String[BUTTONS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface mainFont = Tools.setFont(this);
        setTittle();
        setContentView(R.layout.activity_alphabet);


        for (int i = 1; i <= BUTTONS; i++) {
            int index = i - 1;
            final String letterResource = Tools.getFileResourceName() + i;
            final String[] letter = getAlphabetEntryArray(letterResource);
            int id = getResources().getIdentifier("letter_btn_"+i, "id", getApplicationContext().getPackageName());
            buttons[index] = findViewById(id);
            buttons[index].setTypeface(mainFont);
            buttons[index].setText(letter[0]);
        }
    }

    private String[] getAlphabetEntryArray(String letter) {
        return ((String) getResources().getText(getResources()
            .getIdentifier(letter, STRING, getPackageName()))).split(";");
    }

    private void setTittle() {
        String armPrefix = (Storage.getEasternArmenian() ? getString(R.string.eastern) : getString(R.string.western)) + " ";
        if(this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle(armPrefix + getString(R.string.alphabet_title));
        }
    }
}
