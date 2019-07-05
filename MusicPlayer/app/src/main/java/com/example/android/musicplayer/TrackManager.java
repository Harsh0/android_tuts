package com.example.android.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import java.util.ArrayList;
import java.util.List;

public class TrackManager {
    private static TrackManager sTrackManager;

    private Context mContext;
    private List<Track> mTrackList = new ArrayList<>();
    private MediaPlayer mMediaPlayer;
    private int currentTrackPosition = 0;
    private OnCompletionListener mOnCompletionListener;

    public static TrackManager getInstance(Context context) {
        if (sTrackManager == null) {
            sTrackManager = new TrackManager(context);
        }
        return sTrackManager;
    }

    public TrackManager(Context context) {
        mContext = context;
        addTrack();
        mMediaPlayer = MediaPlayer.create(mContext, getCurrentTrack().getMediaItem());
    }

    private void addTrack() {
        Track track = new Track();
        track.setTitle("Duniya ");
        track.setSubtitle("Luka Chupi");
        track.setArtwork(R.drawable.duniya);
        track.setMediaItem(R.raw.duniya);
        mTrackList.add(track);
        track = new Track();
        track.setTitle("Dariya");
        track.setSubtitle("Bar Bar Dekho");
        track.setArtwork(R.drawable.dariya);
        track.setMediaItem(R.raw.dariya);
        mTrackList.add(track);
        track = new Track();
        track.setTitle("Lambherghini");
        track.setSubtitle("The Doorbean Song");
        track.setArtwork(R.drawable.lamberghini);
        track.setMediaItem(R.raw.lambherghini);
        mTrackList.add(track);
        track = new Track();
        track.setTitle("Duniya 2");
        track.setSubtitle("Luka Chupi 2");
        track.setArtwork(R.drawable.duniya);
        track.setMediaItem(R.raw.duniya);
        mTrackList.add(track);
        track = new Track();
        track.setTitle("Dariya 2");
        track.setSubtitle("Bar Bar Dekho 2");
        track.setArtwork(R.drawable.dariya);
        track.setMediaItem(R.raw.dariya);
        mTrackList.add(track);
        track = new Track();
        track.setTitle("Lambherghini 2");
        track.setSubtitle("The Doorbean Song 2");
        track.setArtwork(R.drawable.lamberghini);
        track.setMediaItem(R.raw.lambherghini);
        mTrackList.add(track);
    }

    public Track getAt(int position) {
        return mTrackList.get(position);
    }

    public int getTotal() {
        return mTrackList.size();
    }

    public int getCurrentTrackPosition() {
        return currentTrackPosition;
    }

    public Track getCurrentTrack() {
        return mTrackList.get(currentTrackPosition);
    }

    public void playOrPause() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            } else {
                mMediaPlayer.start();
            }
        } else {
            play();
        }
    }

    private void play() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
        mMediaPlayer = MediaPlayer.create(mContext, getCurrentTrack().getMediaItem());
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        mMediaPlayer.start();
    }

    public void setCompleteListener(OnCompletionListener listener) {
        mOnCompletionListener = listener;
    }

    public void next() {
        int position = (currentTrackPosition + 1) % mTrackList.size();
        currentTrackPosition = position;
        play();
    }

    public void prev() {
        int position = (currentTrackPosition - 1 + mTrackList.size()) % mTrackList.size();
        currentTrackPosition = position;
        play();
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public void clear() {
        mMediaPlayer.stop();
        mMediaPlayer = null;
    }
}
