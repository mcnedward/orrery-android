package com.mcnedward.orrery.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mcnedward.orrery.model.Planet;
import com.mcnedward.orrery.model.Space;

/**
 * Created by edward on 15/12/15.
 */
public class Renderer {

    private Space mSpace;

    private Paint mPaint;

    public Renderer(Space space) {
        mSpace = space;
        mPaint = new Paint();
    }

    public void render(Canvas canvas) {
        canvas.drawColor(Color.RED);

        for (Planet planet : mSpace.getPlanets()) {
            planet.draw(canvas);
        }
    }

}
