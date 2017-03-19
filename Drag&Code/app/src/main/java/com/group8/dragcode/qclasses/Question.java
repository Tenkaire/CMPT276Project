package com.group8.dragcode.qclasses;

import android.os.Parcelable;
import android.os.Parcel;

import java.util.ArrayList;

/**
 * Created by Liam on 3/8/2017.
 */

public class Question
{
    private String questionKey;
    private String questionTitle;
    private String questionText;
    private String skeletonCode;
    private String hintText;
    private ArrayList<Module> codeModules;

    public Question()
    {
        this.questionKey = "DEF";
        this.questionTitle = "Default Question Title";
        this.questionText = "Default Question Text";
        this.hintText = "Default Skeleton Code";
        this.skeletonCode = "Default Hint Text";
        this.codeModules = new ArrayList<>();
    }

    public Question(String questionKey, String questionTitle, String questionText, String skeletonCode, String hintText, ArrayList<Module> codeModules)
    {
        this.questionKey = questionKey;
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.hintText = hintText;
        this.skeletonCode = skeletonCode;
        this.codeModules = codeModules;
    }

    public String getQuestionKey() { return questionKey; }

    public String getQuestionTitle()
    {
        return questionTitle;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public String getSkeletonCode()
    {
        return skeletonCode;
    }

    public String getHintText()
    {
        return hintText;
    }

    public ArrayList<Module> getCodeModules()
    {
        return codeModules;
    }

}
