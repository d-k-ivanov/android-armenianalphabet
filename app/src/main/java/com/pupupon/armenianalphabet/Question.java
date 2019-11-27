package com.pupupon.armenianalphabet;

import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;

class Question {
    private String question;
    private String answer;
    private String wrong1;
    private String wrong2;
    private String wrong3;
    private String resultText;

    Question(String lt1, String lt2, String lt3, String lt4) {
        String[] rawQuestion = lt1.split(";");
        String[] rawWrong1 = lt2.split(";");
        String[] rawWrong2 = lt3.split(";");
        String[] rawWrong3 = lt4.split(";");

        int[] exclude = {-1};
        int questionPosition = Tools.randInt(0, 2, exclude);
        question = rawQuestion[questionPosition];
        exclude[0] = questionPosition;
        int answerPosition = Tools.randInt(0, 2, exclude);

        answer = rawQuestion[answerPosition];
        wrong1 = rawWrong1[answerPosition];
        wrong2 = rawWrong2[answerPosition];
        wrong3 = rawWrong3[answerPosition];

        resultText = rawQuestion[0] + " - " + rawQuestion[1] + " - " + rawQuestion[2];
    }

    boolean checkQuestion(String s) {
        return s.equals(answer);
    }

    void initQuestion(  TextView questionText,Button[] buttons) {
        //void initQuestion(  TextView questionText,Button answerButton1,Button answerButton2,Button answerButton3, Button answerButton4) {
        questionText.setText(question);
        String[] answers = {answer,wrong1,wrong2,wrong3};
        Collections.shuffle(Arrays.asList(answers));
        for(int i = 0; i < 4; i++){
            buttons[i].setText(answers[i]);
        }
    }

    String getRightAnswer() {
        return this.resultText;
    }
}
