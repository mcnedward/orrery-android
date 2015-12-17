package com.mcnedward.orrery.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 17/12/15.
 */
public class Menu {
    private static String TAG = "Menu";

    private static int menuLeft, menuTop, menuRight, menuBottom;

    private Paint paint;
    private Rect bounds;
    private List<Rect> buttons;

    public Menu() {
        paint = new Paint();
        bounds = new Rect(0, 0, 0, 0);
        initializeButtons();
    }

    private void initializeButtons() {
        buttons = new ArrayList<>();
        buttons.add(new Rect(0, 0, 0, 0));
        buttons.add(new Rect(0, 0, 0, 0));
        buttons.add(new Rect(0, 0, 0, 0));
        buttons.add(new Rect(0, 0, 0, 0));
    }

    public void draw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawRect(bounds, paint);
        for (Rect button : buttons) {
            paint.setColor(Color.GRAY);
            canvas.drawRect(button, paint);
        }
    }

    public void setSize(int width, int height) {
        menuLeft = 0;
        menuTop = height - 80;
        menuRight = width;
        menuBottom = height;

        bounds.set(menuLeft, menuTop, menuRight, menuBottom);

        int buttonPadding = 30;
        int buttonWidth = (width / 4) - (buttonPadding * 2);
        int x = buttonPadding;

        for (Rect button : buttons) {
            button.set(x, menuTop + 10, x + buttonWidth, menuBottom - 10);
            x += buttonWidth + buttonPadding;
        }
    }

    public Rect getBounds() {
        return bounds;
    }

}
