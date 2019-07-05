package com.example.android.musicplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MiniPlayer implements OnDragEventListener, OnSwipeEventListener {
    private static final String TAG = MiniPlayer.class.getSimpleName();

    private TrackManager mTrackManager;

    private final boolean mEnabled;

    private Activity mActivity;

    private MiniPlayerView mMiniPlayerView;

    private DragAnimator mDragAnimator;
    private TouchEventHandler mTouchEventHandler;

    public MiniPlayer() {

        mEnabled = true;

        mDragAnimator = new DragAnimator();

        mTouchEventHandler = new TouchEventHandler();

        mTouchEventHandler.setOnDragEventListener(this);
        mTouchEventHandler.setOnSwipeEventListener(this);
    }

    public void attach (Activity activity) {
        if (mEnabled) {
            mActivity = activity;

            mMiniPlayerView = new MiniPlayerView(activity);

            ((ViewGroup) activity.findViewById(R.id.persistent_player)).addView(mMiniPlayerView);


            mTrackManager = TrackManager.getInstance(activity);
            mTrackManager.setCompleteListener(new OnCompletionListener() {
                @Override
                public void onCompletion(final MediaPlayer mediaPlayer) {
                    next();
                }
            });

            mMiniPlayerView.setControlVisibility();

            setPlaybackListener();

            mMiniPlayerView.setOnTouchListener(mTouchEventHandler.getTouchEventListener());
        }
    }

    private void setPlaybackListener() {
        mMiniPlayerView.setPlayButtonListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                playOrPause();
            }
        });
        mMiniPlayerView.setPrevButtonListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                prev();
            }
        });
        mMiniPlayerView.setNextButtonListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                next();
            }
        });
    }


    private void playOrPause() {
        mTrackManager.playOrPause();
        if (mTrackManager.isPlaying()) {
            mMiniPlayerView.showPauseIconOnPlayButton();
        } else {
            mMiniPlayerView.showPlayIconOnPlayButton();
        }
    }

    private void next() {
        mTrackManager.next();
        mMiniPlayerView.showPauseIconOnPlayButton();
    }

    private void prev() {
        mTrackManager.prev();
        mMiniPlayerView.showPauseIconOnPlayButton();
    }

    public void clear() {
        mTrackManager.clear();
        mTouchEventHandler.clear();
    }

    @Override
    public void onStartDrag() {
        Log.v(TAG, "onStartDrag called");
        mDragAnimator.setView(mMiniPlayerView);
    }

    @Override
    public void onDragLeft(final float x) {

    }

    @Override
    public void onDragRight(final float x) {

    }

    @Override
    public void onDragUp(final float y) {
        mDragAnimator.drag(0, y);
    }

    @Override
    public void onDragDown(final float y) {
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
        mDragAnimator.resetView();
    }
}
