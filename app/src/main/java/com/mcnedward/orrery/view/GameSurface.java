package com.mcnedward.orrery.view;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mcnedward.orrery.model.Menu;
import com.mcnedward.orrery.model.Space;
import com.mcnedward.orrery.util.GameThread;

/**
 * Created by edward on 15/12/15.
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    private static String TAG = "GameSurface";

    public static int WIDTH;
    public static int HEIGHT;

    private Context mContext;
    private GameThread mGameThread;

    private Menu menu;
    private Space space;

    public GameSurface(Context context, Space space) {
        super(context);
        mContext = context;
        this.space = space;

        // TODO Probably put this in Space, or make a World object containing Space and Menu
        menu = new Menu();

        getHolder().addCallback(this);

        WIDTH = getWidth();
        HEIGHT = getHeight();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mGameThread == null) {
            mGameThread = new GameThread(this, space, menu, mContext);
            mGameThread.startGame();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        menu.setSize(width, height);
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
