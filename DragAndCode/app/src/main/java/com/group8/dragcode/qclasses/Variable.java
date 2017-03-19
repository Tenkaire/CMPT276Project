package com.group8.dragcode.qclasses;

/**
 * Created by Liam on 3/18/2017.
 */

public class Variable
{
    private String varKey = "DEF";
    private String varType = "";
    private String varName = "";
    private int lineNum = 0;

    public Variable(String varKey, String varType, String varName, int lineNum)
    {
        this.varKey = varKey;
        this.varType = varType;
        this.varName = varName;
        this.lineNum = lineNum;
    }

    public String getVarKey()
    {
        return varKey;
    }

    public String getVarType()
    {
        return varType;
    }

    public String getVarName()
    {
        return varName;
    }

    public int getLineNum()
    {
        return lineNum;
    }

    public void setLineNum(int lineNum)
    {
        this.lineNum = lineNum;
    }
}
