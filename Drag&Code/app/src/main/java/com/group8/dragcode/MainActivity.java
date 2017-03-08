package com.group8.dragcode;

import android.content.ClipData;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_module = (TextView) findViewById(R.id.tv_hw);
        TextView tv_code = (TextView) findViewById(R.id.tv_code);

        tv_module.setOnTouchListener(new ModuleDragListener());
        tv_code.setOnDragListener(new CodeDropListener());
    }

    private class ModuleDragListener implements View.OnTouchListener
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
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    private class CodeDropListener implements View.OnDragListener
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            if (event.getAction() == DragEvent.ACTION_DROP)
            {
                TextView moduleTV = (TextView) event.getLocalState();
                TextView codeTV = (TextView) v;

                String moduleText = moduleTV.getText().toString();
                String codeText = codeTV.getText().toString();
                String combined = codeText + " " + moduleText;

                codeTV.setText(combined);
            }
            return true;
        }
    }
}
