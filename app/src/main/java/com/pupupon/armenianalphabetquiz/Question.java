package com.pupupon.armenianalphabetquiz;

import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;


public class Question {
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

        int questionPosition = Tools.randInt(1, 5);
        question = rawQuestion[questionPosition];
        //question = "Ա";
        int[] exclude = {questionPosition};
        //answerPosition = getAnswerPosition(questionPosition);
        int answerPosition = Tools.randInt(1, 5, exclude);
        answer = rawQuestion[answerPosition];

        wrong1 = rawWrong1[answerPosition];
        wrong2 = rawWrong2[answerPosition];
        wrong3 = rawWrong3[answerPosition];
        resultText = rawQuestion[1] + " " + rawQuestion[2] + " " + rawQuestion[3] + " " + rawQuestion[4] + " " + rawQuestion[5];

    }


    boolean checkQuestion(String s) {
        return s == answer;
    }

    void initQuestion(  TextView questionText,Button[] buttons) {
    //void initQuestion(  TextView questionText,Button answerButton1,Button answerButton2,Button answerButton3, Button answerButton4) {
        questionText.setText(question);
        String[] answers = {answer,wrong1,wrong2,wrong3};
        Collections.shuffle(Arrays.asList(answers));
        for(int i = 0; i < 4; i++){
            buttons[i].setText(answers[i]);
        }
        //buttons[0].setText(answers[0]);
        //buttons[1].setText(answers[1]);
        //buttons[2].setText(answers[2]);
        //buttons[3].setText(answers[3]);
        //answerButton1.setText(answers[0]);
        //answerButton2.setText(answers[1]);
        //answerButton3.setText(answers[2]);
        //answerButton4.setText(answers[3]);
    }

    String getRightAnswer() {
        return this.resultText;
    }

    int getAnswerPosition(int questionPosition) {
        int answerPosition = questionPosition;
        while(answerPosition == questionPosition){
            answerPosition = Tools.randInt(1,5);
        }
        return answerPosition;
    }

}
