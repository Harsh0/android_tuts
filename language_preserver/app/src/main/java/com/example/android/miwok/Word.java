package com.example.android.miwok;

public class Word {

    private  String defaultTranslation;

    private String miwokTranslation;

    private int imageResourceId = NO_IMAGE_PROVIDED;

    private  int audioResourceId;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId){
            this.defaultTranslation = defaultTranslation;
            this.miwokTranslation = miwokTranslation;
            this.audioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getImageResourceId() { return imageResourceId; }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioTranslation() {
        return audioResourceId;
    }

    /**
     * Returns the string representation of the {@link Word} object.
     */
    @Override
    public String toString() {
        return "Word{" +
            "mDefaultTranslation='" + defaultTranslation + '\'' +
            ", mMiwokTranslation='" + miwokTranslation + '\'' +
            ", mAudioResourceId=" + audioResourceId +
            ", mImageResourceId=" + imageResourceId +
            '}';
    }

}
