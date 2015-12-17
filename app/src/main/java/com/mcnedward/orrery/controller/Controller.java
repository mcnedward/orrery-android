package com.mcnedward.orrery.controller;

import android.content.Context;
import android.graphics.Point;
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

    private Space mSpace;
    private Context mContext;

    private boolean touched;
    private boolean touchDown;
    private boolean touchUp;
    private boolean creating;
    private Point anchor;
    private Point corner;

    private Planet creatingPlanet;

    public Controller(Space space, Context context) {
        mSpace = space;
        mContext = context;
        touched = false;
        anchor = new Point();
        corner = new Point();
    }

    public void update() {
        if (touchDown) {
            Log.d(TAG, "Touch down...");
            touchDown = false;
            creating = true;
            creatingPlanet = new Planet(anchor, mContext);
            mSpace.addPlanet(creatingPlanet);
        }
        if (creating) {
            creatingPlanet.updateBounds(anchor, corner);
        }
//        if (touched) {
//            Planet planet = new Planet(touchPosition);
//            mSpace.addPlanet(planet);
//        }
    }

    public boolean handleTouch(View view, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touched = false;
            touchDown = true;
            anchor.x = x;
            anchor.y = y;
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
            touched = true;
            creating = false;
            return true;
        }
        return false;
    }

}
