package com.example.android.musicplayer;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MiniPlayerContentPagerAdapter extends PagerAdapter {

    private int mTotalCount;
    private TrackManager mTrackManager;

    public MiniPlayerContentPagerAdapter(final int totalCount) {
        mTotalCount = totalCount;
    }

    @Override
    public int getCount() {
        return mTotalCount;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        MiniPlayerContentView miniPlayerContentView = new MiniPlayerContentView(container.getContext());

        mTrackManager = TrackManager.getInstance(container.getContext());
        miniPlayerContentView.setTrackInfo(mTrackManager.getAt(position));
        container.addView(miniPlayerContentView, miniPlayerContentView.getLayoutParams());

        return miniPlayerContentView;
    }

    @Override
    public void destroyItem(@NonNull final ViewGroup container, final int position, @NonNull final Object object) {
        container.removeView((MiniPlayerContentView) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull final View view, @NonNull final Object o) {
        return view == o;
    }
}
