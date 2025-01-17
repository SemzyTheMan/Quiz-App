package com.semzy.myquizapp.service;


import com.semzy.myquizapp.dao.QuestionRepo;
import com.semzy.myquizapp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Question> getFilteredQuestion(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.Direction.valueOf(direction), orderBy);
        return questionRepo.findAll(pageRequest);

    }
}
