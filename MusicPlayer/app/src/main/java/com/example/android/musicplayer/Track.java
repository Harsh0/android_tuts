package com.example.android.musicplayer;

public class Track {
    private String title;
    private String subtitle;
    private int artwork;
    private int mediaItem;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    public int getArtwork() {
        return artwork;
    }

    public void setArtwork(final int artwork) {
        this.artwork = artwork;
    }

    public int getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(final int mediaItem) {
        this.mediaItem = mediaItem;
    }
}
