package com.semzy.myquizapp.dtos;

public class AttemptResponse {

    private Long userId;
    private int Score;
    private  int totalQuestions;

    public AttemptResponse(Long userId, int score, int totalQuestions) {
        this.userId = userId;
        Score = score;
        this.totalQuestions = totalQuestions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
