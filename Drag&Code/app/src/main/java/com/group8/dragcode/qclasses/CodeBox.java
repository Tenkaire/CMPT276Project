package com.group8.dragcode.qclasses;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Liam on 3/12/2017.
 */

public class CodeBox extends AppCompatEditText
{

    ArrayList<String> code;

    public CodeBox(Context context)
    {
        super(context);
        init();
    }

    public CodeBox(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public CodeBox(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        this.setOnDragListener(new CodeDropListener());
        code = new ArrayList<>();
    }

    public void addText(String text, int lineNum)
    {
        if (lineNum > (getLineCount()-1))
        {
            code.add(text);
        }
        else
        {
            code.add(lineNum, text);
        }
        refreshText();
    }

    private void refreshText()
    {
        final int scrollY = getScrollY();
        String fullText = "";
        for (String line : code)
        {
            fullText = fullText + line + "\n";
        }
        setText(fullText);
        post(new Runnable()
        {
            @Override
            public void run()
            {
                scrollTo(0, scrollY);
            }
        });

    }




    private class CodeDropListener implements View.OnDragListener
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            if (event.getAction() == DragEvent.ACTION_DROP)
            {

                if (!(event.getLocalState() instanceof Module)) return true;

                Module module = (Module) event.getLocalState();
                CodeBox codeBox = (CodeBox) v;
                Layout codeLayout = codeBox.getLayout();

                float y = event.getY() + codeBox.getScrollY();
                int lineNum = codeLayout.getLineForVertical((int) y);
                String moduleCode = module.getCode();

                if (module.getNeedsBrackets())
                {
                    codeBox.addText(moduleCode, lineNum);
                    codeBox.addText("{", lineNum+1);
                    codeBox.addText("}", lineNum+1);
                }
                else
                {
                    moduleCode = moduleCode + ";";
                    codeBox.addText(moduleCode, lineNum);
                }

                /*
                if (module.getNeedsBrackets())
                {
                    moduleCode = moduleCode + "\n" +
                                "{\n" +
                                "}\n";
                }
                else
                {
                    moduleCode = moduleCode + ";\n";
                }
                String codeText = codeBox.getText().toString();
                String combined = codeText + " " + moduleCode;

                codeBox.setText(combined);*/
            }
            return true;
        }
    }
}
