package com.pupupon.armenianalphabet;

import static com.pupupon.armenianalphabet.Tools.RAW;
import static com.pupupon.armenianalphabet.Tools.STRING;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlphabetActivity extends AppCompatActivity implements View.OnClickListener {
    private final int TOTAL_LETTERS = 39;
    private final Button[] buttons = new Button[TOTAL_LETTERS];
    private int ALPHABET_ENTRY_INDEX = 1;
    // private String[][]  letters         = new String[TOTAL_LETTERS][];
    // private Typeface mainFont   = Tools.setFont(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface mainFont = Tools.setFont(this);
        setTittle();
        setContentView(R.layout.alphabet_activity);

        for (int i = 1; i <= TOTAL_LETTERS; i++) {
            int index = i - 1;
            final String letterResource = Tools.getFileResourceName() + i;
            final String[] alphabetEntry = getAlphabetEntryArray(letterResource);
            int id = getResources().getIdentifier("letter_btn_" + i, "id", getApplicationContext().getPackageName());
            buttons[index] = findViewById(id);
            buttons[index].setTypeface(mainFont);
            buttons[index].setText(alphabetEntry[ALPHABET_ENTRY_INDEX]);
            buttons[index].setTag(alphabetEntry);
            // buttons[index].setTooltipText(letterResource);
            buttons[index].setOnClickListener(this);
        }

        Button button_shift = findViewById(R.id.letter_btn_shift);
        button_shift.setTypeface(mainFont);
        button_shift.setText("⤊");
        button_shift.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        Button button = (Button) view;

        if (button.getId() == R.id.letter_btn_shift) {
            button.setActivated(ALPHABET_ENTRY_INDEX != 0);
            changeCase();
            return;
        }

        button.setBackgroundResource(R.drawable.button_green);

        // Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.alphabet_popup, null);

        // Create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        Typeface mainFont = Tools.setFont(this);
        String[] letter = (String[]) button.getTag();

        TextView upperCaseText;
        TextView lowerCaseText;
        TextView pronunciationText;

        upperCaseText = popupView.findViewById(R.id.alphabet_popup_uppercase);
        upperCaseText.setText(letter[0]);
        upperCaseText.setTypeface(mainFont);

        lowerCaseText = popupView.findViewById(R.id.alphabet_popup_lowercase);
        lowerCaseText.setText(letter[1]);
        lowerCaseText.setTypeface(mainFont);

        pronunciationText = popupView.findViewById(R.id.alphabet_popup_pronunciation);
        pronunciationText.setText(letter[2]);
        pronunciationText.setTypeface(mainFont);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        Button button_listen = popupView.findViewById(R.id.alphabet_popup_button_listen);
        button_listen.setTypeface(mainFont);
        button_listen.setTag(letter);

        listen(button_listen);

        button_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                listen(view);
            }
        });

        Button button_close = popupView.findViewById(R.id.alphabet_popup_button_close);
        button_close.setTypeface(mainFont);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                resetButtonsBackground();
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void listen(final View view) {
        view.setBackgroundResource(R.drawable.button_green);
        view.setEnabled(false);
        String[] letter = (String[]) view.getTag();
        Tools.playSound(this, getResources().getIdentifier(letter[3], RAW, getPackageName()));
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.button);
                view.setEnabled(true);
            }
        }, 1000);
    }

    private void changeCase() {
        ALPHABET_ENTRY_INDEX = ALPHABET_ENTRY_INDEX == 0 ? 1 : 0;
        for (int index = 0; index < TOTAL_LETTERS; index++) {
            final String[] alphabetEntry = (String[]) buttons[index].getTag();
            buttons[index].setText(alphabetEntry[ALPHABET_ENTRY_INDEX]);
        }
    }

    private String[] getAlphabetEntryArray(String letter) {
        String resources = (String) getResources().getText(getResources()
            .getIdentifier(letter, STRING, getPackageName()));
        resources += ";" + letter;
        return (resources).split(";");
    }

    private void setTittle() {
        if (this.getSupportActionBar() != null) {
            String armPrefix = (Storage.getEasternArmenian() ? getString(R.string.eastern) : getString(R.string.western)) + " ";
            this.getSupportActionBar().setTitle(armPrefix + getString(R.string.alphabet_title));
        }
    }

    private void resetButtonsBackground() {
        for (Button b : buttons) {
            b.setBackgroundResource(R.drawable.button);
        }
    }
}
