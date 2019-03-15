package com.example.android.musicplayer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MiniPlayerContentView extends LinearLayout {

    private Context mContext;
    private TextView mTitle;
    private TextView mSubtitle;
    private ImageView mArtwork;

    public MiniPlayerContentView(@NonNull final Context context) {
        super(context);
        init(context);
    }

    public MiniPlayerContentView(@NonNull final Context context,
                                 @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MiniPlayerContentView(@NonNull final Context context, @Nullable final AttributeSet attrs,
                                 final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View.inflate(context, R.layout.mini_player_content_view, this);
        mTitle = (TextView) findViewById(R.id.MiniPlayerTitle);
        mSubtitle = (TextView) findViewById(R.id.MiniPlayerSubtitle);
        mArtwork = (ImageView) findViewById(R.id.MiniPlayerArtwork);
    }

    public void setTitle(final String title) {
        if (mTitle != null) {
            mTitle.setText(title);
            mTitle.setContentDescription(title);
            mTitle.setVisibility(VISIBLE);
        }
    }

    public void setSubtitle(final String subtitle) {
        if (mSubtitle != null) {
            mSubtitle.setText(subtitle);
            mSubtitle.setContentDescription(subtitle);
            mSubtitle.setVisibility(VISIBLE);
        }
    }

    public void setArtworkDrawable(final Drawable drawable) {
        if (mArtwork != null) {
            mArtwork.setImageDrawable(drawable);
            mArtwork.setVisibility(VISIBLE);
        }
    }

    public void setTrackInfo(final Track track) {
        setTitle(track.getTitle());
        setSubtitle(track.getSubtitle());
        setArtworkDrawable(mContext.getDrawable(track.getArtwork()));
    }
}
