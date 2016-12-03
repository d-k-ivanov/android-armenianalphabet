package com.pupupon.armenianalphabetquiz;

import android.os.Handler;
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
    Button[] buttons =  new Button[4];
    //Button answerButton1;
    //Button answerButton2;
    //Button answerButton3;
   // Button answerButton4;
    TextView resultText;
    Question q;

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
        //answerButton1 = (Button) findViewById(R.id.answer1);
        //answerButton2 = (Button) findViewById(R.id.answer2);
        //answerButton3 = (Button) findViewById(R.id.answer3);
        //answerButton4 = (Button) findViewById(R.id.answer4);
        buttons[0] = (Button) findViewById(R.id.answer1);
        buttons[1] = (Button) findViewById(R.id.answer2);
        buttons[2] = (Button) findViewById(R.id.answer3);
        buttons[3] = (Button) findViewById(R.id.answer4);

        for (Button i: buttons) {
            i.setOnClickListener(this);
        }

        // Init Buttons:
        //answerButton1.setOnClickListener(this);
        //answerButton2.setOnClickListener(this);
        //answerButton3.setOnClickListener(this);
        //answerButton4.setOnClickListener(this);

        // Result Section:
        resultText = (TextView) findViewById(R.id.textResult);

        // Init Result:
        TextViewCompat.setTextAppearance(resultText, R.style.resultSectionHidden);
        resultText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackground));

        // Initialize question by random number:
        String[] letters = Tools.randLetters(1,39);
        String lt1 = (String) getResources().getText(getResources().getIdentifier(letters[0], "string", "com.pupupon.armenianalphabetquiz"));
        String lt2 = (String) getResources().getText(getResources().getIdentifier(letters[1], "string", "com.pupupon.armenianalphabetquiz"));
        String lt3 = (String) getResources().getText(getResources().getIdentifier(letters[2], "string", "com.pupupon.armenianalphabetquiz"));
        String lt4 = (String) getResources().getText(getResources().getIdentifier(letters[3], "string", "com.pupupon.armenianalphabetquiz"));
        q = new Question(lt1,lt2,lt3,lt4);
        //q.initQuestion(questionText,answerButton1,answerButton2,answerButton3,answerButton4);
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

        // Button animation
        final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.button_bounce);
        ButtonBounceInterpolator interpolator = new ButtonBounceInterpolator(0.1, 20);
        bounce.setInterpolator(interpolator);
        button.startAnimation(bounce);
        // Result animation
        final Animation slide = AnimationUtils.loadAnimation(this, R.anim.result_slide);
        resultText.startAnimation(slide);

        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 3000);

    }

    public boolean onLongClick(View view) {
        init();
        return false;
    }


}