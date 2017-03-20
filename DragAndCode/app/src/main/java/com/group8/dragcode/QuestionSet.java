package com.group8.dragcode;

/**
 * Created by Tyler on 2017-03-18.
 *
 * This class is meant to implement ordering of questions with
 * one set corresponding to one difficulty/language pair
 */

public class QuestionSet {
    private String[] questionKeys;
    private int currentIndex;

    public QuestionSet(String[] questionKeys){
        this.questionKeys = questionKeys;
        this.currentIndex = -1;
    }

    public int getNumberQuestions(){
        return questionKeys.length;
    }

    public String getCurrentQuestionKey(){
        return this.questionKeys[this.currentIndex];
    }

    public String getQuestionKeyForIndex(int index){
        this.currentIndex = index;
        return this.questionKeys[index];
    }

    public String getNextQuestionKeys(){
        this.currentIndex += 1;
        return this.questionKeys[this.currentIndex];
    }

    public String getPreviousQuestionKeys(){
        this.currentIndex -= 1;
        return this.questionKeys[this.currentIndex];
    }
}
