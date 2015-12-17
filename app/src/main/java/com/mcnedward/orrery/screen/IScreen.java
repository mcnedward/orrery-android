package com.mcnedward.orrery.screen;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by edward on 17/12/15.
 */
public interface IScreen {

    void update(Canvas canvas);

    boolean handleTouch(View v, MotionEvent event);

    void setSize(int width, int height);

}
