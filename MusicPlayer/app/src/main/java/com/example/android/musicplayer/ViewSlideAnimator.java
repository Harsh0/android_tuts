package com.example.android.musicplayer;

import android.view.View;

public class ViewSlideAnimator {

    private View mCurrentView;
    private View mReplacementView;
    private ViewSwipeAction mSwipeAction = ViewSwipeAction.NONE;

    public enum ViewSwipeAction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NONE
    }

    public void setCurrentView(final View view) {
        mCurrentView = view;
        // TODO: consider logic for determining current view position and what the change in x or y should be once the animation completes
    }

    private void setReplacementView(final View view) {
        mReplacementView = view;
    }

    public void setViewSwipeAction(final ViewSwipeAction swipeAction) {
        mSwipeAction = swipeAction;
    }

    public void executeSwipe() {
        if (mSwipeAction == ViewSwipeAction.NONE) {
            return;
        }
        // TODO perform animation to either just swipe mCurrentView or replace with mReplacementView if it is not null

        // TODO consider how we might be able to extend this logic to a view on screen animating off screen as well as supporting
        // a view that starts off screen and animates on screen
    }
}
