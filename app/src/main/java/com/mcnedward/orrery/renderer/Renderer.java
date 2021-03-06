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

    private Space space;
    private Paint paint;
    private int backgroundColor;

    public Renderer(Space space) {
        this.space = space;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        backgroundColor = Color.BLACK;
    }

    public void render(Canvas canvas) {
        canvas.drawColor(backgroundColor);
        for (Planet planet : space.getPlanets()) {
            planet.draw(canvas);
        }
    }

}
