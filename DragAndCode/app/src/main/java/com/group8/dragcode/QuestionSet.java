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
    private XMLReader xmlReader;

    private QuestionSet(String[] questionKeys, XMLReader xmlReader){
        this.questionKeys = questionKeys;
        this.xmlReader = xmlReader;
        this.currentIndex = -1;
    }

    public int getNumberQuestions(){
        return questionKeys.length;
    }

    public Question getQuestionForIndex(int index){
        this.currentIndex = index;
        return this.xmlReader.getQuestion(questionKeys[index]);
    }

    public Question getNextQuestion(){
        if (this.currentIndex == questionKeys.length - 1){
            throw new IndexOutOfBoundsException("Attempted To Access Question Out Of Set Range");
        }
        this.currentIndex += 1;
        return this.xmlReader.getQuestion(questionKeys[this.currentIndex]);
    }

    public  Question getPreviousQuestion(){
        if (this.currentIndex == 0){
            throw new IndexOutOfBoundsException("Attempted To Access Question Out Of Set Range");
        }
        this.currentIndex -= 1;
        return this.xmlReader.getQuestion(questionKeys[this.currentIndex]);
    }
}
