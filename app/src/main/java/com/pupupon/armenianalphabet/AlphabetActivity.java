package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    public void onClick(final View view) {
        Button button = (Button) view;
        button.setBackgroundResource(R.drawable.button_green);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.alphabet_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        String[] letter = (String[]) button.getTag();

        TextView upperCaseText;
        TextView lowerCaseText;
        TextView pronunciationText;

        upperCaseText = popupView.findViewById(R.id.alphabet_popup_uppercase);
        upperCaseText.setText(letter[0]);

        lowerCaseText = popupView.findViewById(R.id.alphabet_popup_lowercase);
        lowerCaseText.setText(letter[1]);

        pronunciationText = popupView.findViewById(R.id.alphabet_popup_pronunciation);
        pronunciationText.setText(letter[2]);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                resetButtonsBackground();
            }
        });

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void resetButtonsBackground() {
        for(Button b: buttons) {
            b.setBackgroundResource(R.drawable.button);
        }
    }
}
