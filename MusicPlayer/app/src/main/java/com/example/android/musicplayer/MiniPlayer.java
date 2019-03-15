package com.example.android.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;

public class MiniPlayer implements OnDragEventListener, OnSwipeEventListener {
    private static final String TAG = MiniPlayer.class.getSimpleName();

    private TrackManager mTrackManager;

    private MiniPlayerView mMiniPlayerView;
    private MiniPlayerContentView mMiniPlayerContentView;
    private LinearLayout playerControl;
    private LinearLayout extraControl;
    private LinearLayout extraControl2;
    private ImageButton playButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private ImageButton repeatButton;
    private ImageButton shuffleButton;
    private ImageButton thumbsUpButton;
    private ImageButton thumbsDownButton;
    private ImageButton playQueueButton;
    private ImageButton castingButton;
    private ImageButton addToLibraryButton;
    private ImageButton contextMenuButton;

    private DragAnimator mDragAnimator;
    private DragAnimator mDragAnimator2;
    private TouchEventHandler mTouchEventHandler;

    protected final Context mContext;

    public MiniPlayer(final MiniPlayerView view) {
        mContext = view.getContext();
        mMiniPlayerView = view;
        init();

        mTrackManager = TrackManager.getInstance(mContext);
        mTrackManager.setCompleteListener(new OnCompletionListener() {
            @Override
            public void onCompletion(final MediaPlayer mediaPlayer) {
                next();
            }
        });
        mMiniPlayerContentView.setTrackInfo(mTrackManager.getCurrentTrack());

        setControlVisibility();
        setPlaybackListener();

        mDragAnimator = new DragAnimator();
        mDragAnimator2 = new DragAnimator();

        mTouchEventHandler = new TouchEventHandler();

        mMiniPlayerView.setOnTouchListener(mTouchEventHandler.getTouchEventListener());

        mTouchEventHandler.setOnDragEventListener(this);
        mTouchEventHandler.setOnSwipeEventListener(this);
    }

    private void init() {
        mMiniPlayerContentView = (MiniPlayerContentView) mMiniPlayerView.findViewById(R.id.MiniPlayerContent);
        playerControl = (LinearLayout) mMiniPlayerView.findViewById(R.id.MiniPlayerControl);
        playButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerPlayButton);
        nextButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerNextButton);
        prevButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerPrevButton);
        extraControl = (LinearLayout) mMiniPlayerView.findViewById(R.id.MiniPlayerExtraControl);
        repeatButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerRepeatButton);
        shuffleButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerShuffleButton);
        thumbsUpButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerThumbsUpButton);
        thumbsDownButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerThumbsDownButton);
        playQueueButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerQueueButton);
        castingButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerCastingButton);
        extraControl2 = (LinearLayout) mMiniPlayerView.findViewById(R.id.MiniPlayerExtraControl2);
        addToLibraryButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerAddToLibraryButton);
        contextMenuButton = (ImageButton) mMiniPlayerView.findViewById(R.id.MiniPlayerContextMenuButton);
    }

    private void setPlaybackListener() {
        playButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                playOrPause();
            }
        });
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                next();
            }
        });
        prevButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                prev();
            }
        });
    }

    private void setControlVisibility() {
        playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_play_tray));
        playerControl.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        prevButton.setVisibility(View.VISIBLE);
    }

    private void playOrPause() {
        mTrackManager.playOrPause();
        if (mTrackManager.isPlaying()) {
            playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_pause_tray));
        } else {
            playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_play_tray));
        }
    }

    private void next() {
        mTrackManager.next();
        mMiniPlayerContentView.setTrackInfo(mTrackManager.getCurrentTrack());
        playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_pause_tray));
    }

    private void prev() {
        mTrackManager.prev();
        mMiniPlayerContentView.setTrackInfo(mTrackManager.getCurrentTrack());
        playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_pause_tray));
    }

    public void clear() {
        mTrackManager.clear();
        mTouchEventHandler.clear();
    }

    @Override
    public void onStartDrag() {
        Log.v(TAG, "onStartDrag called");
        mDragAnimator.setView(mMiniPlayerContentView);
        mDragAnimator2.setView(mMiniPlayerView);
    }

    @Override
    public void onDragLeft(final float x) {
        mDragAnimator.drag(x,0);
    }

    @Override
    public void onDragRight(final float x) {
        mDragAnimator.drag(x, 0);
    }

    @Override
    public void onDragUp(final float y) {
        mDragAnimator2.drag(0, y);
    }

    @Override
    public void onDragDown(final float y) {
        mDragAnimator2.drag(0, y);
    }

    @Override
    public void onSwipeLeft(final float x) {
        Log.v(TAG, "onSwipeLeft called");
        mDragAnimator.resetView();
        if (x > 325) {
            prev();
        }
    }

    @Override
    public void onSwipeRight(final float x) {
        Log.v(TAG, "onSwipeRight called");
        mDragAnimator.resetView();
        if (x > 325) {
            next();
        }
    }

    @Override
    public void onSwipeDown(final float y) {
        Log.v(TAG, "onSwipeDown called");
        mDragAnimator2.resetView();
    }

    @Override
    public void onSwipeUp(final float y) {
        Log.v(TAG, "onSwipeUp called");
        mDragAnimator2.resetView();
    }
}
