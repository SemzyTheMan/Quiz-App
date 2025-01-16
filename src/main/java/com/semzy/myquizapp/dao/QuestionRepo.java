package com.semzy.myquizapp.dao;

import com.semzy.myquizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

    Question getQuestionById(int id);
}
