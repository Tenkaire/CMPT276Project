package com.group8.dragcode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * Created by Tyler on 2017-03-18.
 *
 * Adapter For QuestionSelection Grid View
 */

public class QuestionButtonAdapter extends BaseAdapter {
    QuestionSet questionSet;
    Context context;
    private QuestionButtonAdapter(Context context, QuestionSet questionSet){
        this.questionSet = questionSet;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questionSet.getNumberQuestions();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null){
            button = new Button(this.context);
            button.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        } else {
            button = (Button) convertView;
        }
        String number = String.valueOf(position + 1);
        if (position < 9){
            number = "0"+number;
        }
        button.setText(number);
        return button;
    }
}
