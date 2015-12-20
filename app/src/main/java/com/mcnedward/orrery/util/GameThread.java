package com.mcnedward.orrery.util;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.mcnedward.orrery.view.GameSurface;

/**
 * Created by Edward on 12/5/2015.
 */
public class GameThread extends Thread {
    private static String TAG = "GameThread";

    private final static int SLEEP_TIME = 40;

    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;
    private boolean running = false;

    public GameThread(GameSurface gameSurface) {
        super();
        this.gameSurface = gameSurface;
        surfaceHolder = gameSurface.getHolder();
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
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if (canvas != null) {
                        // Update and render
                        gameSurface.render(canvas);
                    }
                }
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Log.e(TAG, "Interruption!\n" + e.getMessage());
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
