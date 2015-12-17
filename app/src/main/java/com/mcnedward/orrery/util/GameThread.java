package com.mcnedward.orrery.util;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.mcnedward.orrery.controller.Controller;
import com.mcnedward.orrery.model.Menu;
import com.mcnedward.orrery.model.Space;
import com.mcnedward.orrery.renderer.Renderer;
import com.mcnedward.orrery.screen.IScreen;
import com.mcnedward.orrery.view.GameSurface;

/**
 * Created by Edward on 12/5/2015.
 */
public class GameThread extends Thread {
    private static String TAG = "GameThread";

    private final static int SLEEP_TIME = 40;

    private GameSurface mGameSurface;
    private IScreen screen;
    private SurfaceHolder mSurfaceHolder;

    private boolean running = false;

    public GameThread(GameSurface gameSurface, IScreen screen, Context context) {
        super();
        mGameSurface = gameSurface;
        this.screen = screen;

        mSurfaceHolder = gameSurface.getHolder();
    }

    public void startGame() {
        running = true;
        super.start();
    }

    public void stopGame() {
        running = false;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            canvas = null;
            try {
                canvas = mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder) {
                    if (canvas != null) {
                        // Update and render
                        screen.update(canvas);
                    }
                }
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Log.e(TAG, "Interruption!\n" + e.getMessage());
            } finally {
                if (canvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
