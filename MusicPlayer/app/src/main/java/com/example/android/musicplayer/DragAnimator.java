package com.example.android.musicplayer;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * created by hsnghal on 03/12/2019.
 * This class used to drag view.
 */
public class DragAnimator {

    // default motion buffer zone to prevent view from sliding when user click on view and finger slipped
    private static final int DEFAULT_DRAG_DETECTION_BUFFER = 15;

    private View view;

    private float centerX = 0;
    private float centerY = 0;
    private float mMinDragDetectionBufferX = -1;
    private float mMinDragDetectionBufferY = -1;
    private float dragBufferDistanceX = 0;
    private float dragBufferDistanceY = 0;
    private long resetAnimationDuration = 0;

    public void setView(final View view) {
        this.view = view;

        calculateViewPosition();
        init();
    }

    private void init() {
        if (mMinDragDetectionBufferX < 0) {
            mMinDragDetectionBufferX = DEFAULT_DRAG_DETECTION_BUFFER;
        }
        if (mMinDragDetectionBufferY < 0) {
            mMinDragDetectionBufferY = DEFAULT_DRAG_DETECTION_BUFFER;
        }
        DisplayMetrics dispMetrics = view.getResources().getDisplayMetrics();
        dragBufferDistanceX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMinDragDetectionBufferX, dispMetrics);
        dragBufferDistanceY = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMinDragDetectionBufferY, dispMetrics);
    }

    public void drag(final float x, final float y) {
        if (Math.abs(x) > dragBufferDistanceX || Math.abs(y) > dragBufferDistanceY) {
            view.setX(centerX + x);
            view.setY(centerY + y);
        }
    }

    public void setMinDragDetectionBufferX(final float minDragDetectionBufferX) {
        mMinDragDetectionBufferX = minDragDetectionBufferX;
    }

    public void setMinDragDetectionBufferY(final float minDragDetectionBufferY) {
        mMinDragDetectionBufferY = minDragDetectionBufferY;
    }

    public void setAnimationDurationOnReset(final long duration) {
        resetAnimationDuration = duration;
    }

    public void resetView() {
        view
            .animate()
            .x(centerX)
            .y(centerY)
            .setInterpolator(new LinearInterpolator())
            .setDuration(resetAnimationDuration)
            .start();
    }

    private void calculateViewPosition() {
        centerX = view.getX();
        centerY = view.getY();
    }
}
