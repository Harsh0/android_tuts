package com.example.android.musicplayer;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class MiniPlayer implements OnDragEventListener, OnSwipeEventListener {
    private static final String TAG = MiniPlayer.class.getSimpleName();

    private TextView view;
    private DragAnimator mDragAnimator;
    private TouchEventHandler mTouchEventHandler;

    protected final Context mContext;

    public MiniPlayer(final TextView view) {
        mContext = view.getContext();

        this.view = view;
        mDragAnimator = new DragAnimator();

        mTouchEventHandler = new TouchEventHandler(view);

        view.setOnTouchListener(mTouchEventHandler.getTouchEventListener());

        mTouchEventHandler.setOnDragEventListener(this);
        mTouchEventHandler.setOnSwipeEventListener(this);
    }

    public void clear() {
        mTouchEventHandler.clear();
    }

    @Override
    public void onStartDrag() {
        Log.v(TAG, "onStartDrag called");
        mDragAnimator.setView(view);
    }

    @Override
    public void onDragLeft(final float x) {
        Log.v(TAG, "onDragLeft called with " + x);
        mDragAnimator.drag(x,0);
    }

    @Override
    public void onDragRight(final float x) {
        Log.v(TAG, "onDragRight called with " + x);
        mDragAnimator.drag(x, 0);
    }

    @Override
    public void onDragUp(final float y) {
        Log.v(TAG, "onDragUp called with " + y);
        mDragAnimator.drag(0, y);
    }

    @Override
    public void onDragDown(final float y) {
        Log.v(TAG, "onDragDown called with " + y);
        mDragAnimator.drag(0, y);
    }

    @Override
    public void onSwipeLeft(final float x) {
        Log.v(TAG, "onSwipeLeft called");
    }

    @Override
    public void onSwipeRight(final float x) {
        Log.v(TAG, "onSwipeRight called");
    }

    @Override
    public void onSwipeDown(final float y) {
        Log.v(TAG, "onSwipeDown called");
        mDragAnimator.resetView();
    }

    @Override
    public void onSwipeUp(final float y) {
        Log.v(TAG, "onSwipeUp called");
    }
}
