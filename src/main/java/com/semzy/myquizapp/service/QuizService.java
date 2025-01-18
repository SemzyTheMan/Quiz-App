package com.semzy.myquizapp.service;

import com.semzy.myquizapp.dao.QuestionAnswerRepo;
import com.semzy.myquizapp.entity.AnswerRequest;
import com.semzy.myquizapp.entity.QuestionAnswer;
import com.semzy.myquizapp.entity.QuestionAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuestionAnswerRepo questionAnswerRepo;

    public QuizService(QuestionAnswerRepo questionAnswerRepo) {
        this.questionAnswerRepo = questionAnswerRepo;
    }

    public void answerQuestions(QuestionAnswerDTO answers) {
        for (AnswerRequest answer : answers.getAnswers()) {
            QuestionAnswer existingAnswer = questionAnswerRepo.
                    findByUserIdAndQuestionId(answers.getUserId(),
                            answer.questionId());

            if(existingAnswer!=null){
                questionAnswerRepo.delete(existingAnswer);
            }
        }
        List<QuestionAnswer> answersMain = new ArrayList<>();
        for (AnswerRequest answer:answers.getAnswers()){
            answersMain.add(new QuestionAnswer(answers.getUserId(), answer.questionId(), answer.answer()));
        }

        questionAnswerRepo.saveAll(answersMain);
    }

    public List<QuestionAnswer> getAnswersById(Long id) {
        return questionAnswerRepo.findByUserId(id);
    }


}
