package com.group8.dragcode;

import android.util.Log;

import com.group8.dragcode.qclasses.Question;
import com.group8.dragcode.qclasses.XMLReader;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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

    public Question getCurrentQuestion(){
        try
        {
            return this.xmlReader.getQuestion(questionKeys[this.currentIndex]);
        } catch (IOException | XmlPullParserException | IndexOutOfBoundsException e)
        {
            Log.e("QuestionSet", "Failed to getCurrentQuestion at currentIndex " + currentIndex + ". Error: " + e.getMessage());
        }
        return null;
    }

    public Question getQuestionForIndex(int index){
        this.currentIndex = index;
        try
        {
            return this.xmlReader.getQuestion(questionKeys[index]);
        } catch (IOException | XmlPullParserException | IndexOutOfBoundsException e)
        {
            Log.e("QuestionSet", "Failed to getQuestionForIndex at index " + index + ". Error: " + e.getMessage());
        }
        return null;
    }

    public Question getNextQuestion(){
        if (this.currentIndex == questionKeys.length - 1){
            //throw new IndexOutOfBoundsException("Attempted To Access Question Out Of Set Range");
            return null;
        }
        this.currentIndex += 1;
        try
        {
            return this.xmlReader.getQuestion(questionKeys[this.currentIndex]);
        } catch (IOException | XmlPullParserException | IndexOutOfBoundsException e)
        {
            Log.e("QuestionSet", "Failed to getNextQuestion at index " + currentIndex + ". Error: " + e.getMessage());
        }
        return null;
    }

    public  Question getPreviousQuestion(){
        if (this.currentIndex == 0){
            //throw new IndexOutOfBoundsException("Attempted To Access Question Out Of Set Range");
            return null;
        }
        this.currentIndex -= 1;
        try
        {
            return this.xmlReader.getQuestion(questionKeys[this.currentIndex]);
        } catch (IOException | XmlPullParserException | IndexOutOfBoundsException e)
        {
            Log.e("QuestionSet", "Failed to getNextQuestion at index " + currentIndex + ". Error: " + e.getMessage());
        }
        return null;
    }
}
