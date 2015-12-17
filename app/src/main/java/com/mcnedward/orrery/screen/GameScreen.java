package com.mcnedward.orrery.screen;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.mcnedward.orrery.controller.Controller;
import com.mcnedward.orrery.model.Menu;
import com.mcnedward.orrery.model.Space;
import com.mcnedward.orrery.renderer.Renderer;

/**
 * Created by edward on 17/12/15.
 */
public class GameScreen implements IScreen {

    private Menu menu;
    private Space space;
    private Context context;

    private Controller controller;
    private Renderer renderer;

    public GameScreen(Context context) {
        this.context = context;
        space = new Space();
        menu = new Menu(space);

        controller = new Controller(space, menu, context);
        renderer = new Renderer(space, menu);
    }

    @Override
    public void update(Canvas canvas) {
        controller.update();
        renderer.render(canvas);
    }

    @Override
    public boolean handleTouch(View v, MotionEvent e) {
        return controller.handleTouch(v, e);
    }

    @Override
    public void setSize(int width, int height) {
        menu.setSize(width, height);
    }

    public Menu getMenu() {
        return menu;
    }

    public Space getSpace() {
        return space;
    }

    public Context getContext() {
        return context;
    }
}
