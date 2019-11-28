package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.pupupon.armenianalphabet.Tools.RAW;
import static com.pupupon.armenianalphabet.Tools.STRING;

public class AlphabetActivity extends AppCompatActivity implements View.OnClickListener {
    private int TOTAL_LETTERS   = 39;
//    private Typeface mainFont   = Tools.setFont(this);
    private Button[] buttons    = new Button[TOTAL_LETTERS];
//    private String[] letters    = new String[BUTTONS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface mainFont   = Tools.setFont(this);
        setTittle();
        setContentView(R.layout.alphabet_activity);

        for (int i = 1; i <= TOTAL_LETTERS; i++) {
            int index = i - 1;
            final String letterResource = Tools.getFileResourceName() + i;
            final String[] letter = getAlphabetEntryArray(letterResource);
            int id = getResources().getIdentifier("letter_btn_"+i, "id", getApplicationContext().getPackageName());
            buttons[index] = findViewById(id);
            buttons[index].setTypeface(mainFont);
            buttons[index].setText(letter[0]);
            buttons[index].setTag(letter);
//            buttons[index].setTooltipText(letterResource);
            buttons[index].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(final View view) {
        Typeface mainFont = Tools.setFont(this);
        Button button = (Button) view;
        button.setBackgroundResource(R.drawable.button_green);

        // Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.alphabet_popup, null);

        // Create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

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
            };
        });

        Button button_close = popupView.findViewById(R.id.alphabet_popup_button_close);
        button_close.setTypeface(mainFont);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                popupWindow.dismiss();
            };
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

    private String[] getAlphabetEntryArray(String letter) {
        String resources = (String) getResources().getText(getResources()
            .getIdentifier(letter, STRING, getPackageName()));
        resources += ";" + letter;
        return (resources).split(";");
    }

    private void setTittle() {
        String armPrefix = (Storage.getEasternArmenian() ? getString(R.string.eastern) : getString(R.string.western)) + " ";
        if(this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle(armPrefix + getString(R.string.alphabet_title));
        }
    }

    private void resetButtonsBackground() {
        for(Button b: buttons) {
            b.setBackgroundResource(R.drawable.button);
        }
    }
}
