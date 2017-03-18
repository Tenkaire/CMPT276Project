package com.group8.dragcode;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by Tyler on 2017-03-17.
 */
public class QuestionNumberAdapter extends BaseAdapter {
    private Context context;
    //private QuestionSet questionSet; TODO Uncomment this when QuestionSet Implemented

/** TODO Uncomment this when QuestionClass is implemented
 *  public QuestionNumberAdapter(Context context, QuestionSet questionSet) {
        this.context = context;
        this.questionSet = questionSet;
    }*/

    public QuestionNumberAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        //return this.questionSet.getNumberQuestions(); TODO Uncomment This when QuestionClass is implemented
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        //return (long) this.questionSet.getIdByIndex(position); TODO Uncomment this when QuestionSet is implemented
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = new Button(this.context);
        } else {
            button = (Button) convertView;
        }
        String number = String.valueOf(position + 1);
        if (position + 1 < 10) {
            number = "0" + number;
        }
        button.setText(number);
        button.setTextColor(Color.WHITE);
        button.setBackgroundColor(Color.RED);
        // button.setId(this.questionSet.getIdByIndex(position));  TODO Uncomment With Implementing QuestionSet

        return button;
    }
}
