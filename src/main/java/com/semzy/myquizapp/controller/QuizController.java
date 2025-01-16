package com.semzy.myquizapp.controller;

import com.semzy.myquizapp.dao.AttemptsRepo;
import com.semzy.myquizapp.entity.*;
import com.semzy.myquizapp.service.AttemptsService;
import com.semzy.myquizapp.service.QuestionService;
import com.semzy.myquizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    public AttemptsService attemptsService;

    @Autowired
    public AttemptsRepo attemptsRepo;


    public QuizController(AttemptsService attemptsService, QuizService quizService) {
        this.attemptsService = attemptsService;
        this.quizService = quizService;
    }

    @PostMapping("/answerQuestions")
    public ResponseEntity<?> answerQuestion(@RequestBody List<QuestionAnswerDTO> dtos) {
        try {
            List<QuestionAnswer> answers = dtos.stream().map(dto -> new QuestionAnswer(
                    dto.getUserId(),
                    dto.getQuestionId(),
                    dto.getAnswer()
            )).toList();
            quizService.answerQuestions(answers);
            return new ResponseEntity<>("Success!!"
                    , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse(
                    "Error adding answer",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/submit/{userId}")
    public ResponseEntity<?> submitAnswers(@PathVariable int userId) {
        try {

            List<QuestionAnswer> userAnswers = quizService.getAnswersById(userId);
            Attempts existingAttempt = attemptsService.getAttemptByUserId(userId);

            if(existingAttempt!=null){
                attemptsRepo.delete(existingAttempt);
            }

            userAnswers.forEach(System.out::println);
            int score = 0;
            for (QuestionAnswer answer : userAnswers) {
                Question tempQuestion = questionService.getQuestionById(answer.getQuestion().getId());

                if (tempQuestion.getAnswer().equalsIgnoreCase(answer.getAnswer())) {
                    score = score + 1;
                }
                System.out.println(score);

            }
            Attempts attempt = new Attempts(userId, score, userAnswers.size());
            attemptsService.saveAttempt(attempt);

            return new ResponseEntity<>(attempt
                    , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse(
                    "Error adding answer",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
