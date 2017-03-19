package com.group8.dragcode.qclasses;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.PopupMenuCompat;
import android.support.v7.widget.AppCompatTextView;
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
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.group8.dragcode.R;

/**
 * Created by Liam on 3/19/2017.
 */

public class Blank extends AppCompatTextView
{

    private String answerKey = null;
    private String currentContainedAnswerKey = null;

    public Blank(Context context)
    {
        super(context);
        init();
    }
    public Blank(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public Blank(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 0, 10, 0);

        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.dashback));
        this.setOnDragListener(new CodeDropListener());
        this.setOnTouchListener(new CodeTapListener());
        this.setHint(getResources().getString(R.string.blank_hint));
        this.setLayoutParams(layoutParams);
    }

    public void setAnswerKey(String answerKey)
    {
        this.answerKey = answerKey;
    }

    public String getAnswerKey()
    {
        return answerKey;
    }

    public void setCurrentContainedAnswerKey(String currentContainedAnswerKey)
    {
        this.currentContainedAnswerKey = currentContainedAnswerKey;
    }

    public String getCurrentContainedAnswerKey()
    {
        return currentContainedAnswerKey;
    }

    public boolean checkAnswer()
    {
        if (currentContainedAnswerKey == answerKey)
            return true;
        else
            return false;
    }

    public void setHighlight(boolean highlightOn)
    {
        if (highlightOn)
        {
            setBackgroundColor(ContextCompat.getColor(getContext(), R.color.dashGrey));
        }
        else
        {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.dashback));
        }
    }

    private class CodeDropListener implements View.OnDragListener
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            if (!(event.getLocalState() instanceof Answer)) return true;

            Answer answer = (Answer) event.getLocalState();
            Blank blank = (Blank) v;

            switch (event.getAction())
            {
                case DragEvent.ACTION_DROP:
                    blank.setText(answer.getText());
                    blank.setHighlight(false);
                    setCurrentContainedAnswerKey(answer.getAnswerKey());
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    blank.setHighlight(true);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    blank.setHighlight(false);
                    break;

                case DragEvent.ACTION_DRAG_LOCATION:
                    break;

                default:
                    break;
            }

            return true;
        }
    }

    private class CodeTapListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            final Blank blank = (Blank) v;

            if (blank.getCurrentContainedAnswerKey() == null) return false;

            blank.setHighlight(true);

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    PopupMenu popupMenu = new PopupMenu(getContext(), blank);

                    SpannableString delString = new SpannableString(getResources().getString(R.string.code_clear));
                    delString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.red)), 0, delString.length(), 0);
                    popupMenu.getMenu().add(delString);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                    {
                        @Override
                        public boolean onMenuItemClick(MenuItem item)
                        {
                            Log.i("CodeTapListener", "Checking for clear");
                            if (item.getTitle().toString().equals(getResources().getString(R.string.code_clear)))
                            {
                                Log.i("CodeTapListener", "Reached clear");
                                blank.setText("");
                                blank.setCurrentContainedAnswerKey(null);
                            }
                            blank.setHighlight(false);
                            return false;
                        }
                    });

                    popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener()
                    {
                        @Override
                        public void onDismiss(PopupMenu menu)
                        {
                            blank.setHighlight(false);
                        }
                    });

                    popupMenu.show();
                    break;
                default:
                    break;
            }

            return false;
        }
    }
}
