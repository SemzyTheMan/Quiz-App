package com.semzy.myquizapp.entity;


import java.util.List;

public class QuestionAnswerDTO {
    private Long userId;

    private List<AnswerRequest> answers;

    public QuestionAnswerDTO(Long userId, List<AnswerRequest> answers) {
        this.userId = userId;
        this.answers = answers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequest> answers) {
        this.answers = answers;
    }
}
