package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
    Button[] buttons =  new Button[4];
    TextView resultText;
    Question q;
    Typeface droidSansArmenian;
    Typeface sylfaen;
    Typeface mainFont;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        droidSansArmenian = Typeface.createFromAsset(getAssets(), "fonts/DroidSansArmenian.ttf");
        sylfaen = Typeface.createFromAsset(getAssets(), "fonts/Sylfaen.ttf");
        mainFont = droidSansArmenian;

        init();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    public void init() {
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Question Section
        questionText = (TextView) findViewById(R.id.textQuestion);
        questionText.setOnLongClickListener(this);
        questionText.setTypeface(mainFont);

        // Answer Section
        buttons[0] = (Button) findViewById(R.id.answer1);
        buttons[1] = (Button) findViewById(R.id.answer2);
        buttons[2] = (Button) findViewById(R.id.answer3);
        buttons[3] = (Button) findViewById(R.id.answer4);

        // Init Buttons:
        for (Button i: buttons) {
            i.setOnClickListener(this);
            i.setTypeface(mainFont);
        }

        // Result Section:
        resultText = (TextView) findViewById(R.id.textResult);

        // Init Result:
        TextViewCompat.setTextAppearance(resultText, R.style.resultSectionHidden);
        resultText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackground));

        // Initialize question by random number:
        String[] letters = Tools.randLetters(1,39);
        String lt1 = (String) getResources().getText(getResources().getIdentifier(letters[0], "string", "com.pupupon.armenianalphabet"));
        String lt2 = (String) getResources().getText(getResources().getIdentifier(letters[1], "string", "com.pupupon.armenianalphabet"));
        String lt3 = (String) getResources().getText(getResources().getIdentifier(letters[2], "string", "com.pupupon.armenianalphabet"));
        String lt4 = (String) getResources().getText(getResources().getIdentifier(letters[3], "string", "com.pupupon.armenianalphabet"));
        q = new Question(lt1,lt2,lt3,lt4);
        q.initQuestion(questionText,buttons);

    }

    public void onClick(View view) {

        for (Button b: buttons) {
            b.setEnabled(false);
        }

        Button button = (Button) view;
        if (q.checkQuestion((String) button.getText())) {

            button.setBackgroundResource(R.drawable.button_green);
            TextViewCompat.setTextAppearance(button, R.style.answerCorrectButton);

            TextViewCompat.setTextAppearance(resultText, R.style.resultSectionCorrect);

        }
        else {
            button.setBackgroundResource(R.drawable.button_red);
            TextViewCompat.setTextAppearance(button, R.style.answerIncorrectButton);

            TextViewCompat.setTextAppearance(resultText, R.style.resultSectionIncorrect);
        }

        resultText.setText(q.getRightAnswer());
        resultText.setBackgroundColor(ContextCompat.getColor(this, R.color.resultBackground));
        resultText.setTypeface(mainFont);

        // Button animation
        final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.button_bounce);
        ButtonBounceInterpolator interpolator = new ButtonBounceInterpolator(0.1, 20);
        bounce.setInterpolator(interpolator);
        button.startAnimation(bounce);
        // Result animation
        final Animation slide = AnimationUtils.loadAnimation(this, R.anim.result_slide);
        resultText.startAnimation(slide);

        // Execute some code after 2 seconds have passed
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (Button i: buttons) {
                    if(q.checkQuestion((String)i.getText())){
                        i.setBackgroundResource(R.drawable.button_green);
                        TextViewCompat.setTextAppearance(i, R.style.answerCorrectButton);
                        i.startAnimation(bounce);
                    }
                }
            }
        }, 500);


        // Execute some code after 2 seconds have passed
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 2000);

    }

    public boolean onLongClick(View view) {
        init();
        return false;
    }


}