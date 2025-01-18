package com.semzy.myquizapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_answers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "question_id"})
})
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "answer")
    private String answer;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Long userId, int questionId, String answer) {
        this.user = new Users();
        this.user.setId(userId);

        this.question = new Question();
        this.question.setId(questionId);

        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", user=" + user +
                ", question=" + question +
                ", answer='" + answer + '\'' +
                '}';
    }
}
