package com.group8.dragcode.qclasses;

/**
 * Created by Liam on 3/13/2017.
 */

public class CodeLine
{
    public static final int CODE_TEXT = 0;
    public static final int OPEN_BRACKET = 1;
    public static final int CLOSED_BRACKET = 2;
    public static final int EMPTY_LINE = -1;

    private int lineType = 0;
    private String lineText = "";
    private InLineModule rootModule;

    public CodeLine(int lineType, String lineText, InLineModule rootModule)
    {
        this.lineType = lineType;
        this.lineText = lineText;
        this.rootModule = rootModule;
    }

    public int getLineType()
    {
        return lineType;
    }

    public String getLineText()
    {
        return lineText;
    }

    public InLineModule getRootModule()
    {
        return rootModule;
    }
}