package com.mcnedward.orrery.model;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by edward on 15/12/15.
 */
public interface IFigure {

    void createBounds(Point origin);

    void createBounds(Point origin, Point corner);

    void updateBounds(Point anchor, Point corner);

    void updateBounds(int left, int top, int right, int bottom);

    Rect bounds();

    void draw(Canvas canvas);

}
