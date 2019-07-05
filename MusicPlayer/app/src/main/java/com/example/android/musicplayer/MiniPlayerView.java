package com.example.android.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MiniPlayerView extends FrameLayout implements OnPageChangeListener {

    private Context mContext;

    private int lastPosition = 0;

    private ViewPager mViewPager;
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

    private OnClickListener mPlayButtonListener;
    private OnClickListener mPrevButtonListener;
    private OnClickListener mNextButtonListener;

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
        mContext = context;
        View.inflate(context, R.layout.mini_player_view, this);
        mViewPager = (ViewPager) findViewById(R.id.MiniPlayerContentPager);
        playerControl = (LinearLayout) findViewById(R.id.MiniPlayerControl);
        playButton = (ImageButton) findViewById(R.id.MiniPlayerPlayButton);
        nextButton = (ImageButton) findViewById(R.id.MiniPlayerNextButton);
        prevButton = (ImageButton) findViewById(R.id.MiniPlayerPrevButton);
        extraControl = (LinearLayout) findViewById(R.id.MiniPlayerExtraControl);
        repeatButton = (ImageButton) findViewById(R.id.MiniPlayerRepeatButton);
        shuffleButton = (ImageButton) findViewById(R.id.MiniPlayerShuffleButton);
        thumbsUpButton = (ImageButton) findViewById(R.id.MiniPlayerThumbsUpButton);
        thumbsDownButton = (ImageButton) findViewById(R.id.MiniPlayerThumbsDownButton);
        playQueueButton = (ImageButton) findViewById(R.id.MiniPlayerQueueButton);
        castingButton = (ImageButton) findViewById(R.id.MiniPlayerCastingButton);
        extraControl2 = (LinearLayout) findViewById(R.id.MiniPlayerExtraControl2);
        addToLibraryButton = (ImageButton) findViewById(R.id.MiniPlayerAddToLibraryButton);
        contextMenuButton = (ImageButton) findViewById(R.id.MiniPlayerContextMenuButton);

        mViewPager.setAdapter(new MiniPlayerContentPagerAdapter(TrackManager.getInstance(context).getTotal()));
        mViewPager.addOnPageChangeListener(this);
    }

    public void setControlVisibility() {
        playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_play_tray));
        playerControl.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        prevButton.setVisibility(View.VISIBLE);
    }

    public void setPlayButtonListener(OnClickListener listener) {
        mPlayButtonListener = listener;
        playButton.setOnClickListener(listener);
    }

    public void setPrevButtonListener(OnClickListener listener) {
        mPrevButtonListener = listener;
        prevButton.setOnClickListener(listener);
    }

    public void setNextButtonListener(OnClickListener listener) {
        mNextButtonListener = listener;
        nextButton.setOnClickListener(listener);
    }

    public void showPauseIconOnPlayButton() {
        playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_pause_tray));
    }

    public void showPlayIconOnPlayButton() {
        playButton.setImageDrawable(mContext.getDrawable(R.drawable.player_btn_play_tray));
    }

    @Override
    public void onPageScrolled(final int i, final float v, final int i1) {

    }

    @Override
    public void onPageSelected(final int i) {
        int currentPosition = mViewPager.getCurrentItem();
        if (currentPosition > lastPosition) {
            // next
            mNextButtonListener.onClick(mViewPager);
        } else if (currentPosition < lastPosition) {
            // prev
            mPrevButtonListener.onClick(mViewPager);
        }
        lastPosition = currentPosition;
    }

    @Override
    public void onPageScrollStateChanged(final int i) {
        int currentPosition = mViewPager.getCurrentItem();
        if (currentPosition > lastPosition) {
            // next
            mNextButtonListener.onClick(mViewPager);
        } else if (currentPosition < lastPosition) {
            // prev
            mPrevButtonListener.onClick(mViewPager);
        }
        lastPosition = currentPosition;
    }
}

