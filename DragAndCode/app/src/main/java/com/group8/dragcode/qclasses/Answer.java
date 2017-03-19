package com.group8.dragcode.qclasses;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Liam on 3/19/2017.
 */

public class Answer extends AppCompatButton
{
    private String answerKey;
    private String answerText;

    public Answer(Context context)
    {
        super(context);
        init();
    }
    public Answer(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public  Answer(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        this.setOnTouchListener(new AnswerDragListener());
    }

    public String getAnswerKey()
    {
        return answerKey;
    }

    public void setAnswerKey(String answerKey)
    {
        this.answerKey = answerKey;
    }

    public String getAnswerText()
    {
        return answerText;
    }

    public void setAnswerText(String answerText)
    {
        this.answerText = answerText;
        this.setText(answerText);
    }




    private class AnswerDragListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                ClipData clipData = ClipData.newPlainText("", "");
                View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                {
                    v.startDrag(clipData, dragShadowBuilder, v, 0);
                }
                else
                {
                    v.startDragAndDrop(clipData, dragShadowBuilder, v, 0);
                }
            }
            return false;
        }
    }
}
