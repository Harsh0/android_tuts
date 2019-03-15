package com.example.android.musicplayer;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * created by hsnghal on 03/12/2019.
 * This class is used to perform actual drag animation and reset view based on certain configurations.
 */
public class DragAnimator {

    // default motion buffer zone to prevent view from sliding when user click on view and finger slipped
    private static final int DEFAULT_DRAG_DETECTION_BUFFER = 15;

    private View mView;

    private float mCenterX = 0;
    private float mCenterY = 0;
    private float mMinDragDetectionBufferX = -1;
    private float mMinDragDetectionBufferY = -1;
    private float mDragBufferDistanceX = 0;
    private float mDragBufferDistanceY = 0;
    private long mResetAnimationDuration = 0;

    public void setView(final View view) {
        mView = view;

        calculateViewPosition();
        init();
    }

    public void drag(final float x, final float y) {
        if (Math.abs(x) > mDragBufferDistanceX || Math.abs(y) > mDragBufferDistanceY) {
            mView.setX(mCenterX + x);
            mView.setY(mCenterY + y);
        }
    }

    public void setMinDragDetectionBufferX(final float minDragDetectionBufferX) {
        mMinDragDetectionBufferX = minDragDetectionBufferX;
    }

    public void setMinDragDetectionBufferY(final float minDragDetectionBufferY) {
        mMinDragDetectionBufferY = minDragDetectionBufferY;
    }

    public void setAnimationDurationOnReset(final long duration) {
        mResetAnimationDuration = duration;
    }

    public void resetView() {
        mView
            .animate()
            .x(mCenterX)
            .y(mCenterY)
            .setInterpolator(new LinearInterpolator())
            .setDuration(mResetAnimationDuration)
            .start();
    }

    private void init() {
        if (mMinDragDetectionBufferX < 0) {
            mMinDragDetectionBufferX = DEFAULT_DRAG_DETECTION_BUFFER;
        }
        if (mMinDragDetectionBufferY < 0) {
            mMinDragDetectionBufferY = DEFAULT_DRAG_DETECTION_BUFFER;
        }
        DisplayMetrics dispMetrics = mView.getResources().getDisplayMetrics();
        mDragBufferDistanceX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMinDragDetectionBufferX, dispMetrics);
        mDragBufferDistanceY = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMinDragDetectionBufferY, dispMetrics);
    }

    private void calculateViewPosition() {
        mCenterX = mView.getX();
        mCenterY = mView.getY();
    }
}
