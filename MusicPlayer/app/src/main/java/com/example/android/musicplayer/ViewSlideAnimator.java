package com.example.android.musicplayer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewSlideAnimator {

    private ViewSwipeAction mSwipeAction = ViewSwipeAction.NONE;

    private ViewPager mViewPager;

    public ViewSlideAnimator(final ViewPager viewPager) {
        mViewPager = viewPager;
    }

    public enum ViewSwipeAction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NONE
    }

    public void setCurrentView(final View view) {
        // TODO: consider logic for determining current view position and what the change in x or y should be once the animation completes
        mViewPager.addView(view, 0, view.getLayoutParams());
    }


    public void setViewSwipeAction(final ViewSwipeAction swipeAction) {
        mSwipeAction = swipeAction;
    }

    public void executeSwipe() {
        switch (mSwipeAction) {
            case LEFT:

                break;
            case RIGHT:

                break;
            case UP:

                break;
            case DOWN:

                break;
            case NONE:
            default:
                return;
        }
        // TODO perform animation to either just swipe mCurrentView or replace with mReplacementView if it is not null

        // TODO consider how we might be able to extend this logic to a view on screen animating off screen as well as supporting
        // a view that starts off screen and animates on screen
    }
}
