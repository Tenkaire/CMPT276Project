package com.group8.dragcode.qclasses;

import java.util.ArrayList;

/**
 * Created by Liam on 3/13/2017.
 */

public class InLineModule
{
    private String moduleKey = "";

    private boolean deletable = false;
    private boolean acceptsArguments = false;
    private boolean acceptsComparisons = false;
    private boolean needsBrackets = false;

    private int rootLineNum = 0;
    private int startBrackLineNum = 0;
    private int endBrackLineNum = 0;

    private int indentLevel = 0;

    private InLineModule parent = null;
    private ArrayList<InLineModule> children = null;

    public InLineModule(String moduleKey, boolean deletable, boolean acceptsArguments, boolean acceptsComparisons, boolean needsBrackets, int indentLevel, int rootLineNum, InLineModule parent)
    {
        this.moduleKey = moduleKey;
        this.deletable = deletable;
        this.acceptsArguments = acceptsArguments;
        this.acceptsComparisons = acceptsComparisons;
        this.needsBrackets = needsBrackets;
        this.indentLevel = indentLevel;
        this.rootLineNum = rootLineNum;
        this.parent = parent;
    }

    public int getIndentLevel()
    {
        return indentLevel;
    }

    public InLineModule getParent()
    {
        return parent;
    }

    public void setParent(InLineModule parent)
    {
        this.parent = parent;
    }

    public void addChild(InLineModule module)
    {
        children.add(module);
    }

    public boolean getNeedsBrackets()
    {
        return needsBrackets;
    }

    public void setRootLineNum(int rootLineNum)
    {
        this.rootLineNum = rootLineNum;
    }

    public int getRootLineNum()
    {
        return rootLineNum;
    }

    public void setStartBrackLineNum(int startBrackLineNum)
    {
        this.startBrackLineNum = startBrackLineNum;
    }

    public int getStartBrackLineNum()
    {
        return startBrackLineNum;
    }

    public void setEndBrackLineNum(int endBrackLineNum)
    {
        this.endBrackLineNum = endBrackLineNum;
    }

    public int getEndBrackLineNum()
    {
        return endBrackLineNum;
    }

    public boolean getDeletable()
    {
        return deletable;
    }

    public boolean getAcceptsArguments()
    {
        return acceptsArguments;
    }

    public boolean getAcceptsComparisons()
    {
        return acceptsComparisons;
    }
}
