package com.group8.dragcode.qclasses;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.PopupMenu;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.group8.dragcode.R;

import java.util.ArrayList;

/**
 * Created by Liam on 3/12/2017.
 */

public class CodeBox extends AppCompatEditText
{

    private Context context;
    private ArrayList<CodeLine> code;
    private ArrayList<Variable> globalVars;
    private Question currentQuestion = null;

    private boolean drawOverbar = false;
    private boolean drawHighlight = false;
    private int currentLine = 0;

    public CodeBox(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public CodeBox(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CodeBox(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init()
    {
        this.setOnDragListener(new CodeDropListener());
        this.setOnTouchListener(new CodeTapListener());
        code = new ArrayList<>();
        code.add(new CodeLine(CodeLine.EMPTY_LINE, "", null));
        globalVars = new ArrayList<>();
    }

    public void setCurrentQuestion(Question currentQuestion)
    {
        this.currentQuestion = currentQuestion;
    }

    public void addModule(InLineModule ilModule, String moduleCode)
    {
        if (!ilModule.getNeedsBrackets())
        {
            moduleCode = moduleCode + ";";
        }

        int bump = (ilModule.getNeedsBrackets()) ? 3 : 1;
        for (int i = ilModule.getRootLineNum(); i < getLineCount(); i++)
        {
            CodeLine cl = code.get(i);
            switch (cl.getLineType())
            {
                case CodeLine.CODE_TEXT:
                    cl.getRootModule().setRootLineNum(cl.getRootModule().getRootLineNum()+bump);
                    break;
                case CodeLine.OPEN_BRACKET:
                    cl.getRootModule().setStartBrackLineNum(cl.getRootModule().getStartBrackLineNum()+bump);
                    break;
                case CodeLine.CLOSED_BRACKET:
                    cl.getRootModule().setEndBrackLineNum(cl.getRootModule().getEndBrackLineNum()+bump);
                    break;
                default:
                    break;
            }
        }

        CodeLine newCL = new CodeLine(CodeLine.CODE_TEXT, moduleCode, ilModule);
        code.add(ilModule.getRootLineNum(), newCL);

        if (ilModule.getNeedsBrackets())
        {
            CodeLine openBrack = new CodeLine(CodeLine.OPEN_BRACKET, "{", ilModule);
            CodeLine closeBrack = new CodeLine(CodeLine.CLOSED_BRACKET, "}", ilModule);
            ilModule.setStartBrackLineNum(ilModule.getRootLineNum()+1);
            ilModule.setEndBrackLineNum(ilModule.getRootLineNum()+2);
            code.add(ilModule.getStartBrackLineNum(), openBrack);
            code.add(ilModule.getEndBrackLineNum(), closeBrack);
        }

        refreshText();
    }

    private void refreshText()
    {
        final int scrollY = getScrollY();
        final int scrollX = getScrollX();
        String fullText = "";
        for (CodeLine line : code)
        {
            if (line.getLineType() == CodeLine.EMPTY_LINE)
            {
                break;
            }
            else
            {
                for (int i = line.getRootModule().getIndentLevel(); i > 0; i--)
                {
                    fullText = fullText + "\t";
                }
                fullText = fullText + line.getLineText() + "\n";
            }
        }
        setText(fullText);
        post(new Runnable()
        {
            @Override
            public void run()
            {
                scrollTo(scrollX, scrollY);
            }
        });

    }

    private void deleteCurrentLine()
    {
        InLineModule moduleToDelete = code.get(currentLine).getRootModule();
        int endBrackLineNum = moduleToDelete.getEndBrackLineNum();
        int lineDiff = endBrackLineNum - currentLine;

        for (int i = lineDiff; i >= 0; i--)
        {
            code.remove(currentLine);
        }

        for (int i = currentLine; i < code.size(); i++)
        {
            CodeLine cl = code.get(i);
            switch (cl.getLineType())
            {
                case CodeLine.CODE_TEXT:
                    cl.getRootModule().setRootLineNum(cl.getRootModule().getRootLineNum()-(lineDiff+1));
                    break;
                case CodeLine.OPEN_BRACKET:
                    cl.getRootModule().setStartBrackLineNum(cl.getRootModule().getStartBrackLineNum()-(lineDiff+1));
                    break;
                case CodeLine.CLOSED_BRACKET:
                    cl.getRootModule().setEndBrackLineNum(cl.getRootModule().getEndBrackLineNum()-(lineDiff+1));
                    break;
                default:
                    break;
            }
        }

        refreshText();
    }

    private void addArgumentCurrentLine()
    {

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (drawOverbar)
        {
            Rect overbar = new Rect();
            getLineBounds(currentLine, overbar);

            overbar.bottom = overbar.top + 5;

            Paint ulColor = new Paint();
            ulColor.setColor(ContextCompat.getColor(context, R.color.colorPrimary));

            canvas.drawRect(overbar, ulColor);
        }

        if (drawHighlight)
        {
            Rect highlight = new Rect();
            getLineBounds(currentLine, highlight);

            Paint ulColor = new Paint();
            ulColor.setColor(ContextCompat.getColor(context, R.color.colorPrimary));

            canvas.drawRect(highlight, ulColor);
        }

        super.onDraw(canvas);
    }




    private class CodeDropListener implements View.OnDragListener
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            if (!(event.getLocalState() instanceof Module)) return true;

            Module module = (Module) event.getLocalState();
            CodeBox codeBox = (CodeBox) v;
            Layout codeLayout = codeBox.getLayout();

            float y = event.getY() + codeBox.getScrollY();
            int lineNum = codeLayout.getLineForVertical((int) Math.round(y));
            if (lineNum > getLineCount()) lineNum = getLineCount();

            switch (event.getAction())
            {
                case DragEvent.ACTION_DROP:
                    CodeLine droppedOn = code.get(lineNum);
                    InLineModule ilModule;

                    switch(droppedOn.getLineType())
                    {
                        case CodeLine.CODE_TEXT:
                            ilModule = new InLineModule(module.getModuleId(), true, module.getAcceptsArguments(), module.getAcceptsComparisons(), module.getNeedsBrackets(), droppedOn.getRootModule().getIndentLevel(), lineNum, droppedOn.getRootModule().getParent());
                            codeBox.addModule(ilModule, module.getCode());
                            break;
                        case CodeLine.OPEN_BRACKET:
                            ilModule = new InLineModule(module.getModuleId(), true, module.getAcceptsArguments(), module.getAcceptsComparisons(), module.getNeedsBrackets(), droppedOn.getRootModule().getIndentLevel()+1, lineNum+1, droppedOn.getRootModule());
                            codeBox.addModule(ilModule, module.getCode());
                            break;
                        case CodeLine.CLOSED_BRACKET:
                            ilModule = new InLineModule(module.getModuleId(), true, module.getAcceptsArguments(), module.getAcceptsComparisons(), module.getNeedsBrackets(), droppedOn.getRootModule().getIndentLevel()+1, lineNum, droppedOn.getRootModule());
                            codeBox.addModule(ilModule, module.getCode());
                            break;
                        case CodeLine.EMPTY_LINE:
                            ilModule = new InLineModule(module.getModuleId(), true, module.getAcceptsArguments(), module.getAcceptsComparisons(), module.getNeedsBrackets(), 0, lineNum, null);
                            codeBox.addModule(ilModule, module.getCode());
                            break;
                        default:
                            break;
                    }

                    drawOverbar = false;
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    drawOverbar = true;
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    drawOverbar = false;
                    break;

                case DragEvent.ACTION_DRAG_LOCATION:
                    currentLine = lineNum;
                    invalidate();
                    break;

                default:
                    break;
            }

            return true;
        }
    }

    private class CodeTapListener implements View.OnTouchListener
    {
        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction() == MotionEvent.ACTION_UP)
            {
                CodeBox codeBox = (CodeBox) v;
                Layout codeLayout = codeBox.getLayout();

                float y = event.getY() + codeBox.getScrollY();
                int lineNum = codeLayout.getLineForVertical((int) Math.round(y));
                if (lineNum > getLineCount()) return true;

                CodeLine tapped = code.get(lineNum);

                drawHighlight = true;
                currentLine = lineNum;
                invalidate();

                if (tapped.getLineType() == CodeLine.CODE_TEXT)
                {
                    InLineModule ilModule = tapped.getRootModule();

                    if (ilModule.getDeletable() || ilModule.getAcceptsArguments() || ilModule.getAcceptsComparisons())
                    {
                        PopupMenu popupMenu = new PopupMenu(context, v);
                        popupMenu.setOnDismissListener(new CodeMenuDismissListener());
                        popupMenu.setOnMenuItemClickListener(new CodeMenuItemClickListener());

                        if (ilModule.getAcceptsArguments())
                        {
                            popupMenu.getMenu().add("Add Argument");
                        }

                        if (ilModule.getAcceptsComparisons())
                        {
                            popupMenu.getMenu().add("Add Comparison");
                        }

                        if (ilModule.getDeletable())
                        {
                            SpannableString deleteString = new SpannableString("Delete");
                            deleteString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, deleteString.length(), 0);
                            popupMenu.getMenu().add(deleteString);
                        }

                        popupMenu.show();
                    }
                }
                else
                {
                    drawHighlight = false;
                    invalidate();
                }
            }

            return false;
        }
    }

    private class CodeMenuDismissListener implements PopupMenu.OnDismissListener
    {
        @Override
        public void onDismiss(PopupMenu menu)
        {
            drawHighlight = false;
            invalidate();
        }
    }

    private class CodeMenuItemClickListener implements PopupMenu.OnMenuItemClickListener
    {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            String menuItemText = item.getTitle().toString();

            if (menuItemText.equals("Delete"))
            {
                deleteCurrentLine();
            }

            if (menuItemText.equals("Add Argument"))
            {
                addArgumentCurrentLine();
            }

            return true;
        }
    }
}
