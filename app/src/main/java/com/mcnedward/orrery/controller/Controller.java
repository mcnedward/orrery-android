package com.mcnedward.orrery.controller;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mcnedward.orrery.model.Planet;
import com.mcnedward.orrery.model.Space;

/**
 * Created by edward on 15/12/15.
 */
public class Controller {
    private static String TAG = "Controller";

    private Space space;
    private Context context;

    private boolean touchDown, touchUp;
    private boolean touchingSpace;
    private boolean creating;
    private Point anchor;
    private Point corner;

    private Planet creatingPlanet;

    public Controller(Space space, Context context) {
        this.space = space;
        this.context = context;
        anchor = new Point();
        corner = new Point();
    }

    public void update() {
        if (touchDown) {
            Log.d(TAG, "Touch down...");
            if (!creating) {
                creating = true;
                creatingPlanet = new Planet(anchor, context);
                space.addPlanet(creatingPlanet);
            } else {
                creatingPlanet.updateBounds(anchor, corner);
            }
        }
        if (touchUp) {
            creating = false;
        }
    }

    public boolean handleTouch(View view, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchDown = true;
            touchUp = false;
            // TODO This is to account for finger size, this should be researched later
            anchor.x = x - 50;
            anchor.y = y - 50;
            corner.x = x;
            corner.y = y;
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            corner.x = x;
            corner.y = y;
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            corner.x = x;
            corner.y = y;
            touchDown = false;
            touchUp = true;
            return true;
        }
        return false;
    }

}
