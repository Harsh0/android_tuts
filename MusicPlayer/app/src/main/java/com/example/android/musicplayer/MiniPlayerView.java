package com.example.android.musicplayer;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


public class MiniPlayerView extends FrameLayout {

    private MiniPlayerContentView mMiniPlayerContentView;

    public MiniPlayerView(@NonNull final Context context) {
        super(context);
        init(context);
    }

    public MiniPlayerView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MiniPlayerView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.mini_player_view, this);
        mMiniPlayerContentView = (MiniPlayerContentView) findViewById(R.id.MiniPlayerContent);
    }

    @Override
    protected void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // logic for showing and hiding

    }
}

