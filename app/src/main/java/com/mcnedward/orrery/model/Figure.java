package com.mcnedward.orrery.model;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by edward on 15/12/15.
 */
public abstract class Figure implements IFigure {

    protected Rect bounds;

    public Figure() {
        this(new Point(0, 0));
    }

    public Figure(Point origin) {
        createBounds(origin);
    }

    @Override
    public void updateBounds(Point anchor, Point corner) {
        bounds = new Rect(anchor.x, anchor.y, corner.x, corner.y);
    }

    @Override
    public void createBounds(Point origin) {
        bounds = new Rect(origin.x, origin.y, origin.x + 100, origin.y + 100);
    }

    @Override
    public void createBounds(Point origin, Point corner) {
        bounds = new Rect(origin.x, origin.y, corner.x, corner.y);
    }

    @Override
    public Rect bounds() {
        return bounds;
//        return new Rect(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

}
