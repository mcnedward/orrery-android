package com.mcnedward.orrery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mcnedward.orrery.R;
import com.mcnedward.orrery.model.Menu;
import com.mcnedward.orrery.model.Space;

/**
 * Created by edward on 17/12/15.
 */
public class GameView extends RelativeLayout {
    private static String TAG = "GameView";

    private Menu menu;
    private Space space;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_game, this, true);

        GameSurface gameSurface = new GameSurface(getContext());
        OrreryMenuView orreryMenu = new OrreryMenuView(getContext());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        addView(gameSurface);
        addView(orreryMenu, params);
    }

}
