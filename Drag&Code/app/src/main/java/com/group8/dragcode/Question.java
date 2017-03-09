package com.group8.dragcode;

/**
 * Created by Liam on 3/8/2017.
 */

public class Question
{

    private String questionTitle;
    private String questionText;
    private String skeletonCode;
    private String hintText;
    private int[] codeModules;

    public Question()
    {
        this.questionTitle = "Default Question Title";
        this.questionText = "Default Question Text";
        this.hintText = "Default Skeleton Code";
        this.skeletonCode = "Default Hint Text";
        this.codeModules = codeModules;
    }

    public Question(String questionTitle, String questionText, String skeletonCode, String hintText, int[] codeModules)
    {
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.hintText = hintText;
        this.skeletonCode = skeletonCode;
        this.codeModules = codeModules;
    }

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

    public int[] getCodeModules()
    {
        return codeModules;
    }

}
