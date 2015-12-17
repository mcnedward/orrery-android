package com.mcnedward.orrery.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.mcnedward.orrery.R;

/**
 * Created by edward on 15/12/15.
 */
public class Planet extends Figure {
    private static String TAG = "Planet";

    private static int PLANET_SIZE = 200;
    private static int MIN_RADIUS = 20;

    private String mPlanetName;
    private Paint mPaint;

    private boolean draggingLeft, draggingUp;

    public Planet(Context context) {
        this(new Point(0, 0), context);
    }

    public Planet(Point point, Context context) {
        super(point);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
    }

    @Override
    public void updateBounds(Point anchor, Point corner) {
        if (corner.x < anchor.x && corner.x <= anchor.x - PLANET_SIZE)
            corner.x = anchor.x - PLANET_SIZE;
        if (corner.x > anchor.x && corner.x >= anchor.x + PLANET_SIZE)
            corner.x = anchor.x + PLANET_SIZE;
        if (corner.y > anchor.y && corner.y >= anchor.y + PLANET_SIZE)
            corner.y = anchor.y + PLANET_SIZE;
        if (corner.y < anchor.y && corner.y <= anchor.y - PLANET_SIZE)
            corner.y = anchor.y - PLANET_SIZE;

        draggingLeft = corner.x < anchor.x;
        draggingUp = corner.y < anchor.y;

        super.updateBounds(anchor, corner);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect planetBounds = getPlanetBounds();
        Point centerPoint = getCenterPoint();
        int radius = (Math.abs(planetBounds.width()) / 2) > MIN_RADIUS ? (Math.abs(planetBounds.width()) / 2) : MIN_RADIUS;
        canvas.drawCircle(centerPoint.x, centerPoint.y, radius, mPaint);
    }

    public Rect getPlanetBounds() {
        Rect r = bounds();

        if (r.width() > PLANET_SIZE) {
            adjustRect(r);
        }
        if (r.height() > PLANET_SIZE) {
            adjustRect(r);
        }

        return r;
    }

    private void adjustRect(Rect r) {
        int left, top, right, bottom;
        if (draggingLeft) {
            left = r.left;
            right = r.left + PLANET_SIZE;
        } else {
            left = r.left - PLANET_SIZE;
            right = r.left;
        }
        if (draggingUp) {
            top = r.top - PLANET_SIZE;
            bottom = r.top;
        } else {
            top = r.top;
            bottom = r.top + PLANET_SIZE;
        }
        r.set(left, top, right, bottom);
    }

    public Point getCenterPoint() {
        Rect bounds = getPlanetBounds();
        int radius = Math.abs(bounds.width()) / 2;
        if (radius < MIN_RADIUS)
            radius = MIN_RADIUS;
        int cx, cy;
        if (draggingLeft)
            cx = bounds.left - radius;
        else
            cx = bounds.left + radius;
        if (draggingUp)
            cy = bounds.top - radius;
        else
            cy = bounds.top + radius;
        return new Point(cx, cy);
    }

}
