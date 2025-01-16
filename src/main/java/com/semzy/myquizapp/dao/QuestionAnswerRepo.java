package com.semzy.myquizapp.dao;

import com.semzy.myquizapp.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionAnswerRepo extends JpaRepository<QuestionAnswer, Integer> {
    List<QuestionAnswer> findByUserId(int userId);

    QuestionAnswer findByUserIdAndQuestionId(int userId, int questionId);
}
