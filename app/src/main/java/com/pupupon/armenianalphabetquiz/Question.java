package com.pupupon.armenianalphabetquiz;


import android.widget.Button;
import android.widget.TextView;

public class Question {
    private String question;
    private int questionPosition;
    private String answer;
    private int answerPosition;
    private String wrong1;
    private String wrong2;
    private String wrong3;
    private String resultText;

    Question() {


        question = "Ա";
        questionPosition = 1;
        answer = "[aɪb]";
        answerPosition = 3;
        wrong1 = "[bɛn]";
        wrong2 = "[gim]";
        wrong3 = "[dɑ]";
        resultText = "Ա ա [aɪb] a[ɑ] Айб";

    }

    boolean checkQuestion(String s) {
        if (s == answer)
            return true ;
        else
            return false;
    }

    void initQuestion(
            TextView questionText,
            Button answerButton1,
            Button answerButton2,
            Button answerButton3,
            Button answerButton4) {
        questionText.setText(question);
        answerButton1.setText(answer);
        answerButton2.setText(wrong1);
        answerButton3.setText(wrong2);
        answerButton4.setText(wrong3);
    }

    String getRightAnswer() {
        return this.resultText;
    }


}
