package com.example.android.musicplayer;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * created by hsnghal on 03/11/19.
 * This class take view to support drag and swipe actions of that view and invoke appropriate listeners
 */
public class TouchEventHandler {

    private View view;
    private OnSwipeEventListener mOnSwipeEventListener;
    private OnDragEventListener mOnDragEventListener;

    private float posX = 0; // current x position of view when motion started
    private float posY = 0; // current y position of view when motion started
    private float posDiff = 0; // to track position difference on swipe event

    private boolean gestureCompleted;
    private DragAction dragAction = DragAction.NONE;

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
                    // getY() get relative distance motionevent to jewel, since jewel keep moving
                    // this would result in jewel twitching in vertical drag
                    // use getRawY() to calculate absolute distance from original position
                    float translateY = motionEvent.getRawY() - posY;

                    if (mOnDragEventListener != null) {
                        if (Math.abs(translateY) > Math.abs(translateX)) {
                            if (translateY < 0) {
                                dragAction = DragAction.UP;
                                mOnDragEventListener.onDragUp(translateY);
                            } else {
                                dragAction = DragAction.DOWN;
                                mOnDragEventListener.onDragDown(translateY);
                            }
                            posDiff = Math.abs(translateY);
                        } else {
                            if (translateX < 0) {
                                dragAction = DragAction.LEFT;
                                mOnDragEventListener.onDragLeft(translateX);
                            } else {
                                dragAction = DragAction.RIGHT;
                                mOnDragEventListener.onDragRight(translateX);
                            }
                            posDiff = Math.abs(translateX);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    DragAction finalAction = dragAction;

                    executeAction(finalAction);
                    break;
            }
            return true;
        }
    };

    public TouchEventHandler(final View v) {
        view = v;
    }

    public OnTouchListener getTouchEventListener() {
        return mOnTouchListener;
    }

    public void setOnSwipeEventListener(OnSwipeEventListener listener) {
        mOnSwipeEventListener = listener;
    }

    public void setOnDragEventListener(OnDragEventListener listener) {
        mOnDragEventListener = listener;
    }

    public void clear() {
        // clear out resources, to be called from onDestroy of activity to prevent memory leak
        view = null;
        mOnSwipeEventListener = null;
        mOnDragEventListener = null;
    }

    private void executeAction(DragAction action) {
        if (mOnSwipeEventListener == null || gestureCompleted) {
            return;
        }

        switch (action) {
            case RIGHT:
                mOnSwipeEventListener.onSwipeRight(posDiff);
                break;
            case LEFT:
                mOnSwipeEventListener.onSwipeLeft(posDiff);
                break;
            case UP:
                mOnSwipeEventListener.onSwipeUp(posDiff);
                break;
            case DOWN:
                mOnSwipeEventListener.onSwipeDown(posDiff);
                break;
            case NONE:
            default:
                break;
        }
        gestureCompleted = true;
        this.dragAction = DragAction.NONE;
    }
}
