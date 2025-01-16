package com.semzy.myquizapp.service;


import com.semzy.myquizapp.dao.QuestionRepo;
import com.semzy.myquizapp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public Question addQuestion(Question question){
        return  questionRepo.save(question);
    }
    public List<Question> loadAllQuestions(){

        return questionRepo.findAll();
    }
    public Question getQuestionById(int id){
        return  questionRepo.getQuestionById(id);
    }
}
