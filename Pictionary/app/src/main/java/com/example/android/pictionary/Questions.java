package com.example.android.pictionary;

public class Questions {
    private int questionId;
    private String imageUrl;
    private int difficulty;
    private String answer;
    private String userAnswer;
    private boolean userAnswerCorrect;

    public boolean isUserAnswerCorrect() {
        return userAnswerCorrect;
    }

    public void setUserAnswerCorrect(final boolean userAnswerCorrect) {
        this.userAnswerCorrect = userAnswerCorrect;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(final String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(final int questionId) {
        this.questionId = questionId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(final int difficulty) {
        this.difficulty = difficulty;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }
}
