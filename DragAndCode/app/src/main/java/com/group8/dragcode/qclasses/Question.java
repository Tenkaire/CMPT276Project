package com.group8.dragcode.qclasses;

import android.os.Parcelable;
import android.os.Parcel;
import android.widget.LinearLayout;
import android.widget.TableRow;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Liam on 3/8/2017.
 */

public class Question
{
    private String questionKey;
    private String questionTitle;
    private String hintText;
    private String language;
    private String difficulty;
    private ArrayList<Answer> codeAnswers;
    private ArrayList<LinearLayout> codeRows;
    private ArrayList<String> codeOutputs;

    public Question()
    {
        this.questionKey = "DEF";
        this.questionTitle = "Default Question Title";
        this.hintText = "Default Hint Text";
        this.language = "Java";
        this.difficulty = "Easy";
        this.codeAnswers = new ArrayList<>();
        this.codeRows = new ArrayList<>();
        this.codeOutputs = new ArrayList<>();
    }

    public Question(String questionKey, String questionTitle, String hintText, String language, String difficulty, ArrayList<Answer> codeAnswers, ArrayList<LinearLayout> codeRows, ArrayList<String> codeOutputs)
    {
        this.questionKey = questionKey;
        this.questionTitle = questionTitle;
        this.hintText = hintText;
        this.language = language;
        this.difficulty = difficulty;
        this.codeAnswers = codeAnswers;
        this.codeRows = codeRows;
        this.codeOutputs = codeOutputs;
    }

    public String getQuestionKey()
    {
        return questionKey;
    }

    public String getQuestionTitle()
    {
        return questionTitle;
    }

    public String getHintText()
    {
        return hintText;
    }

    public String getLanguage()
    {
        return language;
    }

    public String getDifficulty()
    {
        return difficulty;
    }

    public ArrayList<Answer> getCodeAnswers()
    {
        return codeAnswers;
    }

    public ArrayList<LinearLayout> getCodeRows()
    {
        return codeRows;
    }

    public ArrayList<String> getCodeOutputs()
    {
        return codeOutputs;
    }
}
