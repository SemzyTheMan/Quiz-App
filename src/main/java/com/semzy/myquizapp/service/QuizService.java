package com.semzy.myquizapp.service;

import com.semzy.myquizapp.dao.QuestionAnswerRepo;
import com.semzy.myquizapp.entity.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuestionAnswerRepo questionAnswerRepo;

    public QuizService(QuestionAnswerRepo questionAnswerRepo) {
        this.questionAnswerRepo = questionAnswerRepo;
    }

    public void answerQuestions(List<QuestionAnswer> answers) {
        for (QuestionAnswer answer : answers) {
            QuestionAnswer existingAnswer = questionAnswerRepo.
                    findByUserIdAndQuestionId(answer.getUser().getId(),
                            answer.getQuestion().getId());

            if(existingAnswer!=null){
                questionAnswerRepo.delete(existingAnswer);
            }
        }

        questionAnswerRepo.saveAll(answers);
    }

    public List<QuestionAnswer> getAnswersById(Long id) {
        return questionAnswerRepo.findByUserId(id);
    }
}
