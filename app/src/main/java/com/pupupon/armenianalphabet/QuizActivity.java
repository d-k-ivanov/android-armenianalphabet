package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.os.Handler;

import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsActivity;

import static com.pupupon.armenianalphabet.Tools.STRING;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTIONL_PASS;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_FAIL;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.CATEGORY_QUIZ_EVENTS;

public class QuizActivity extends GoogleAnalyticsActivity implements OnClickListener, OnLongClickListener {
    private Button[] buttons =  new Button[4];
    private TextView resultText;
    private Question q;
    private Typeface mainFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFont = Tools.setFont(this);
        setTittle();

        init();
    }

    public void init() {
        setContentView(R.layout.activity_quiz);

        // Question Section
        TextView questionText = (TextView) findViewById(R.id.textQuestion);
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
        String lt1 = getPronunciation(letters[0]);
        String lt2 = getPronunciation(letters[1]);
        String lt3 = getPronunciation(letters[2]);
        String lt4 = getPronunciation(letters[3]);
        q = new Question(lt1,lt2,lt3,lt4);
        q.initQuestion(questionText, buttons);
    }

    private String getPronunciation(String letter) {
        return (String) getResources().getText(getResources()
            .getIdentifier(letter, STRING, getPackageName()));
    }

    public void onClick(View view) {

        for (Button b: buttons) {
            b.setEnabled(false);
        }

        Button button = (Button) view;
        if (q.checkQuestion((String) button.getText())) {
            setEvent(CATEGORY_QUIZ_EVENTS, ACTIONL_PASS, q.getRightAnswer());
            button.setBackgroundResource(R.drawable.button_green);
            TextViewCompat.setTextAppearance(button, R.style.answerCorrectButton);

            TextViewCompat.setTextAppearance(resultText, R.style.resultSectionCorrect);

        }
        else {
            setEvent(CATEGORY_QUIZ_EVENTS, ACTION_FAIL, q.getRightAnswer());
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

    private void setTittle() {
        String armPrefix = (Storage.getEasternArmenian() ? getString(R.string.eastern) : getString(R.string.western)) + " ";
        this.getSupportActionBar().setTitle(armPrefix + getString(R.string.quiz_title));
    }
}
