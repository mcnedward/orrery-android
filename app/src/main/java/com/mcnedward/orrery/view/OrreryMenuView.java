package com.mcnedward.orrery.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mcnedward.orrery.R;
import com.mcnedward.orrery.model.Space;

/**
 * Created by edward on 17/12/15.
 */
public class OrreryMenuView extends LinearLayout {

    private Space space;
    private Context context;
    private View thisView;

    public OrreryMenuView(Space space, Context context) {
        super(context);
        this.space = space;
        this.context = context;

        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        thisView = inflater.inflate(R.layout.view_orrery_menu, this, true);

        setButtonActions();
    }

    private void setButtonActions() {
        setClearButtonAction();
    }

    private void setClearButtonAction() {
        Button button = (Button) thisView.findViewById(R.id.btnClear);
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                space.refresh();
                return false;
            }
        });
    }

}
