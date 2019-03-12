package com.example.android.musicplayer;

public interface OnDragEventListener {
    void onStartDrag();
    void onDragLeft(float x);
    void onDragRight(float x);
    void onDragUp(float y);
    void onDragDown(float y);
}

