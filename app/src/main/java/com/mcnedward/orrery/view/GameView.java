package com.mcnedward.orrery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.mcnedward.orrery.R;
import com.mcnedward.orrery.model.Space;

/**
 * Created by edward on 17/12/15.
 */
public class GameView extends RelativeLayout {
    private static String TAG = "GameView";

    private Space space;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        space = new Space();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_game, this, true);

        GameSurface gameSurface = new GameSurface(space, getContext());
        OrreryMenuView orreryMenu = new OrreryMenuView(space, getContext());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        addView(gameSurface);
        addView(orreryMenu, params);
    }

}
