package com.mcnedward.orrery.view;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.mcnedward.orrery.model.Menu;
import com.mcnedward.orrery.model.Space;
import com.mcnedward.orrery.screen.GameScreen;
import com.mcnedward.orrery.screen.IScreen;
import com.mcnedward.orrery.util.GameThread;

/**
 * Created by edward on 15/12/15.
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private static String TAG = "GameSurface";

    public static int WIDTH;
    public static int HEIGHT;

    private IScreen screen;
    private Context context;
    private GameThread mGameThread;

    public GameSurface(final IScreen screen, Context context) {
        super(context);
        this.screen = screen;
        this.context = context;

        getHolder().addCallback(this);

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return screen.handleTouch(v, event);
            }
        });

        WIDTH = getWidth();
        HEIGHT = getHeight();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mGameThread == null) {
            mGameThread = new GameThread(this, screen, context);
            mGameThread.startGame();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        screen.setSize(width, height);
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
