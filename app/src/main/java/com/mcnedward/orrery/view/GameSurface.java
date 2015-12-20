package com.mcnedward.orrery.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.mcnedward.orrery.controller.Controller;
import com.mcnedward.orrery.model.Space;
import com.mcnedward.orrery.renderer.Renderer;
import com.mcnedward.orrery.util.GameThread;

/**
 * Created by edward on 15/12/15.
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private static String TAG = "GameSurface";

    public static int WIDTH;
    public static int HEIGHT;

    private GameThread mGameThread;

    private Controller controller;
    private Renderer renderer;

    public GameSurface(Space space, Context context) {
        super(context);

        controller = new Controller(space, context);
        renderer = new Renderer(space);

        getHolder().addCallback(this);

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return controller.handleTouch(v, event);
            }
        });
    }

    public void render(Canvas canvas) {
        controller.update();
        renderer.render(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mGameThread == null) {
            mGameThread = new GameThread(this);
            mGameThread.startGame();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mGameThread != null) {
            mGameThread.stopGame();
            boolean retry = false;
            while (retry) {
                try {
                    mGameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    Log.e(TAG, "Interrupted in GameSurface!\n" + e.getMessage());
                }
            }
            mGameThread = null;
        }
    }
}
