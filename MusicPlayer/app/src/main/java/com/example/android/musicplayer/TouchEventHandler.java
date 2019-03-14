package com.example.android.musicplayer;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * created by hsnghal on 03/11/19.
 * This class take view to support drag and swipe actions of that view and invoke appropriate listeners
 */
public class TouchEventHandler {

    private OnSwipeEventListener mOnSwipeEventListener;
    private OnDragEventListener mOnDragEventListener;

    private float posX = 0; // x coordinate of view when motion started
    private float posY = 0; // y coordinate of view when motion started
    private float posDiff = 0; // to track position difference on swipe event

    private boolean gestureCompleted;
    private DragAction dragAction = DragAction.NONE;

    private float mMinSwipeDetectionX = 0;
    private float mMinSwipeDetectionY = 0;

    private enum DragAction {
        RIGHT,
        LEFT,
        UP,
        DOWN,
        NONE // no action
    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    posX = motionEvent.getRawX();
                    posY = motionEvent.getRawY();

                    if (mOnDragEventListener != null) {
                        mOnDragEventListener.onStartDrag();
                    }
                    gestureCompleted = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    float translateX = motionEvent.getRawX() - posX;
                    float translateY = motionEvent.getRawY() - posY;

                    if (Math.abs(translateY) > Math.abs(translateX)) {
                        if (translateY < 0) {
                            dragAction = DragAction.UP;
                        } else {
                            dragAction = DragAction.DOWN;
                        }
                        posDiff = Math.abs(translateY);
                    } else {
                        if (translateX < 0) {
                            dragAction = DragAction.LEFT;
                        } else {
                            dragAction = DragAction.RIGHT;
                        }
                        posDiff = Math.abs(translateX);
                    }
                    onDrag(dragAction, translateX, translateY);
                    break;
                case MotionEvent.ACTION_UP:
                    onSwipe(dragAction);
                    break;
            }
            return true;
        }
    };

    public OnTouchListener getTouchEventListener() {
        return mOnTouchListener;
    }

    public void setOnSwipeEventListener(final OnSwipeEventListener listener) {
        mOnSwipeEventListener = listener;
    }

    public void setOnDragEventListener(final OnDragEventListener listener) {
        mOnDragEventListener = listener;
    }

    public void setMinSwipeDetectionX(final float minSwipeDetectionX) {
        mMinSwipeDetectionX = minSwipeDetectionX;
    }

    public void setMinSwipeDetectionY(final float minSwipeDetectionY) {
        mMinSwipeDetectionY = minSwipeDetectionY;
    }

    public void clear() {
        // clear out resources, to be called from onDestroy of activity to prevent memory leak
        mOnSwipeEventListener = null;
        mOnDragEventListener = null;
    }

    private void onDrag(final DragAction action, final float x, final float y) {
        if (mOnDragEventListener == null) {
            return;
        }
        switch (action) {
            case RIGHT:
                mOnDragEventListener.onDragRight(x);
                break;
            case LEFT:
                mOnDragEventListener.onDragLeft(x);
                break;
            case DOWN:
                mOnDragEventListener.onDragDown(y);
                break;
            case UP:
                mOnDragEventListener.onDragUp(y);
                break;
            case NONE:
            default:
                break;
        }
    }

    private void onSwipe(final DragAction action) {
        if (mOnSwipeEventListener == null || gestureCompleted) {
            dragAction = DragAction.NONE;
            return;
        }

        switch (action) {
            case RIGHT:
                if (posDiff > mMinSwipeDetectionX) {
                    mOnSwipeEventListener.onSwipeRight(posDiff);
                }
                break;
            case LEFT:
                if (posDiff > mMinSwipeDetectionX) {
                    mOnSwipeEventListener.onSwipeLeft(posDiff);
                }
                break;
            case UP:
                if (posDiff > mMinSwipeDetectionY) {
                    mOnSwipeEventListener.onSwipeUp(posDiff);
                }
                break;
            case DOWN:
                if (posDiff > mMinSwipeDetectionY) {
                    mOnSwipeEventListener.onSwipeDown(posDiff);
                }
                break;
            case NONE:
            default:
                break;
        }
        gestureCompleted = true;
        dragAction = DragAction.NONE;
    }
}
