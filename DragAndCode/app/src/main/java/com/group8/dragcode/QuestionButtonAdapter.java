package com.group8.dragcode;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Tyler on 2017-03-18.
 */

public class QuestionButtonAdapter extends BaseAdapter {
    QuestionSet questionSet;
    private QuestionButtonAdapter(QuestionSet questionSet){
        this.questionSet = questionSet;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
