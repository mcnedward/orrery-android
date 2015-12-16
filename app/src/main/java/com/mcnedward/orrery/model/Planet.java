package com.mcnedward.orrery.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by edward on 15/12/15.
 */
public class Planet extends Figure {
    private static String TAG = "Planet";

    private static int PLANET_SIZE = 200;
    private static int MIN_RADIUS = 20;

    private String mPlanetName;
    private Paint mPaint;

    public Planet() {
        this(new Point(0, 0));
    }

    public Planet(Point point) {
        super(point);
        mPaint = new Paint(Color.GREEN);
    }

    @Override
    public void updateBounds(Point anchor, Point corner) {
        int dragX = corner.x, dragY = corner.y;
        if (dragX < anchor.x && dragX <= anchor.x - PLANET_SIZE)
            corner.x = anchor.x - PLANET_SIZE;
        if (dragX > corner.x && dragX >= anchor.x + PLANET_SIZE)
            corner.x = anchor.x + PLANET_SIZE;
        if (dragY > anchor.y && dragY >= dragY + PLANET_SIZE)
            corner.y = anchor.y + PLANET_SIZE;
        if (dragY < anchor.y && dragY <= anchor.y - PLANET_SIZE)
            corner.y = anchor.y - PLANET_SIZE;
        super.updateBounds(anchor, corner);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect planetBounds = getPlanetBounds();
        Point centerPoint = getCenterPoint();
        int radius = (planetBounds.width() / 2) > MIN_RADIUS ? (planetBounds.width() / 2) : MIN_RADIUS;
        canvas.drawCircle(centerPoint.x, centerPoint.y, radius, mPaint);
//        canvas.drawOval(planetBounds.left, planetBounds.top, planetBounds.right, planetBounds.bottom, mPaint);
//        canvas.drawRect(planetBounds.left, planetBounds.top, planetBounds.right, planetBounds.bottom, mPaint);
    }

    public Rect getPlanetBounds() {
        Rect r = bounds();

        if (r.width() > PLANET_SIZE) {
            int right = r.left + PLANET_SIZE;
            r.set(r.left, r.top, right, r.bottom);
        }
        if (r.height() > PLANET_SIZE) {
            int bottom = r.top + PLANET_SIZE;
            r.set(r.left, r.top, r.right, bottom);
        }

        return r;
    }

    public Point getCenterPoint() {
        Rect bounds = getPlanetBounds();
        int anchorX = bounds.left, anchorY = bounds.top;
        int radius = bounds.width() / 2;
        if (radius < MIN_RADIUS)
            radius = MIN_RADIUS;
        int cx = anchorX + radius;
        int cy = anchorY + radius;
        Log.d(TAG, "CX: " + cx + "; CY: " + cy);
        return new Point(cx, cy);
    }

}
