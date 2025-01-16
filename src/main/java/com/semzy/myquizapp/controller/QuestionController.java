package com.semzy.myquizapp.controller;

import com.semzy.myquizapp.entity.CustomResponse;
import com.semzy.myquizapp.entity.Question;
import com.semzy.myquizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        try {
            Question savedQuestion = questionService.addQuestion(question);
            return new ResponseEntity<>(new CustomResponse(
                    "Question added successfully",
                    HttpStatus.CREATED.value(),
                    savedQuestion
            ), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new CustomResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST.value(),
                    null
            ), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse(
                    "Error adding question",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestions() {
        try {
            List<Question> questions = questionService.loadAllQuestions();
            if (questions.isEmpty()) {
                return new ResponseEntity<>(new CustomResponse(
                        "No questions found",
                        HttpStatus.NOT_FOUND.value(),
                        null
                ), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new CustomResponse(
                    "Questions retrieved successfully",
                    HttpStatus.OK.value(),
                    questions
            ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse(
                    "Error retrieving questions",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}