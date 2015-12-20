package com.mcnedward.orrery.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.mcnedward.orrery.R;
import com.mcnedward.orrery.model.Space;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 17/12/15.
 */
public class OrreryMenuView extends LinearLayout {
    private static String TAG = "OrreryMenuView";

    private Space space;
    private Context context;

    public OrreryMenuView(Space space, Context context) {
        super(context);
        this.space = space;
        this.context = context;

        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        inflate(context, R.layout.view_orrery_menu, this);

        initializeButtons();
    }

    private void initializeButtons() {
        RadioButton btnCreate = (RadioButton) findViewById(R.id.btnCreate);
        RadioButton btnResize = (RadioButton) findViewById(R.id.btnResize);
        RadioButton btnDelete = (RadioButton) findViewById(R.id.btnDelete);
        Button btnClear = (Button) findViewById(R.id.btnClear);
        setClearButtonAction(btnClear);
    }

    private void setClearButtonAction(Button button) {
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                space.refresh();
                return false;
            }
        });
    }

}
