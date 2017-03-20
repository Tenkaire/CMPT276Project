package com.group8.dragcode;

import android.content.Intent;
import android.view.View;

public class QuestionButtonOnClickListener implements View.OnClickListener{
    String qkey;

    public QuestionButtonOnClickListener(String qkey){
        this.qkey = qkey;
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(v.getContext(), QuestionActivity.class);
        intent.putExtra("QKEY", qkey);
        v.getContext().startActivity(intent);
    }
}
