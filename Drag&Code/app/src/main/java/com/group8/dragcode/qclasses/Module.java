package com.group8.dragcode.qclasses;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Liam on 3/11/2017.
 */

public class Module extends AppCompatButton
{

    private String moduleKey = "DEF";
    private String moduleName = "DEFAULT MODULE";
    private String moduleCode = "foo.bar()";
    private boolean needsBrackets = false;

    public Module(Context context, String moduleKey, String moduleName, String moduleCode, boolean needsBrackets)
    {
        super(context);

        setOnTouchListener(new ModuleDragListener());

        this.moduleKey = moduleKey;
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.needsBrackets = needsBrackets;

        this.setText(this.moduleName);
    }

    public String getModuleId() { return moduleKey; }

    public String getProperName() { return moduleName; }

    public String getCode() { return moduleCode; }

    public boolean getNeedsBrackets() { return needsBrackets; }




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
}
