package com.pupupon.armenianalphabetquiz;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnLongClickListener, Runnable  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
/*
        //Demo
        OnClickListener questionClick = new OnClickListener() {
            @Override
            public void onClick(View view) {
                questionText.setText(getResources().getText(R.string.answer4));
            }
        };
        questionText.setOnClickListener(questionClick);
*/

    }

    public void init() {
        setContentView(R.layout.activity_main);
        // Question Section
        final  TextView questionText = (TextView) findViewById(R.id.textQuestion);
        questionText.setOnLongClickListener(this);

        // Answer Section
        final Button answerButton1 = (Button) findViewById(R.id.answer1);
        final Button answerButton2 = (Button) findViewById(R.id.answer2);
        final Button answerButton3 = (Button) findViewById(R.id.answer3);
        final Button answerButton4 = (Button) findViewById(R.id.answer4);

        // Init Buttons:
        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);
        // Result Section:
        final TextView resultText = (TextView) findViewById(R.id.textResult);

        // Init Result:
        TextViewCompat.setTextAppearance(resultText, R.style.resultSectionHidden);
        resultText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackground));
    }

    public void onClick(View view) {

        Button button = (Button) view;

        button.setBackgroundResource(R.drawable.button_green);
        //button.setBackgroundResource(R.drawable.button_red);
        TextViewCompat.setTextAppearance(button, R.style.answerCorrectButton);

        final TextView resultText = (TextView) findViewById(R.id.textResult);
        TextViewCompat.setTextAppearance(resultText, R.style.resultSection);
        resultText.setBackgroundColor(ContextCompat.getColor(this, R.color.answerBackground));

    }

    public boolean onLongClick(View view) {
        init();
        return false;
    }

    public void run() {

    }

}