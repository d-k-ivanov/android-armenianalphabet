package com.pupupon.armenianalphabetquiz;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnLongClickListener {
    // Vars:
    TextView questionText;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button answerButton4;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setContentView(R.layout.activity_main);
        // Question Section
        questionText = (TextView) findViewById(R.id.textQuestion);
        questionText.setOnLongClickListener(this);

        // Answer Section
        answerButton1 = (Button) findViewById(R.id.answer1);
        answerButton2 = (Button) findViewById(R.id.answer2);
        answerButton3 = (Button) findViewById(R.id.answer3);
        answerButton4 = (Button) findViewById(R.id.answer4);
        // Init Buttons:
        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);

        // Result Section:
        resultText = (TextView) findViewById(R.id.textResult);

        // Init Result:
        TextViewCompat.setTextAppearance(resultText, R.style.resultSectionHidden);
        resultText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackground));
    }

    public void onClick(View view) {

        Button button = (Button) view;

        //button.setBackgroundResource(R.drawable.button_green);
        //button.setBackgroundResource(R.drawable.button_red);
        //TextViewCompat.setTextAppearance(button, R.style.answerCorrectButton);

        final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.button_bounce);
        ButtonBounceInterpolator interpolator = new ButtonBounceInterpolator(0.1, 20);
        bounce.setInterpolator(interpolator);
        button.startAnimation(bounce);

        TextViewCompat.setTextAppearance(resultText, R.style.resultSection);
        resultText.setBackgroundColor(ContextCompat.getColor(this, R.color.resultBackground));
        final Animation slide = AnimationUtils.loadAnimation(this, R.anim.result_slide);
        resultText.startAnimation(slide);

    }

    public boolean onLongClick(View view) {
        init();
        return false;
    }

}