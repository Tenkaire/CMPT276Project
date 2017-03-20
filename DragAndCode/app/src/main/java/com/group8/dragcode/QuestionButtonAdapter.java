package com.group8.dragcode;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
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

    public QuestionButtonAdapter(Context context, QuestionSet questionSet){
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
        View grid;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.question_selection_grid_layout, parent, false);
        } else {
            grid = (View) convertView;
        }
        Button button = (Button) grid.findViewById(R.id.questionButton);
        String number;
        if (position + 1 < 10){
            number = "0" + String.valueOf(position + 1);
        } else {
            number = String.valueOf(position + 1);
        }
        button.setText(number);

        return grid;
    }
}
