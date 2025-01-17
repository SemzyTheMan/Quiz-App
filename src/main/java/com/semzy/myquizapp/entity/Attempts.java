package com.semzy.myquizapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_attempts")
public class Attempts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "score")
    private int score;

    @Column(name = "total_questions")
    private int totalQuestions;

    public Attempts() {
    }

    public Attempts(Long userId, int score, int totalQuestions) {
        this.totalQuestions = totalQuestions;
        this.score = score;

        this.user = new Users();
        user.setId(userId);
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
