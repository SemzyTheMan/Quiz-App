package com.semzy.myquizapp.service;

import com.semzy.myquizapp.dao.AttemptsRepo;
import com.semzy.myquizapp.entity.Attempts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptsService {

    @Autowired
    private AttemptsRepo attemptsRepo;

    public Attempts saveAttempt(Attempts attempts) {

        return attemptsRepo.save(attempts);

    }

    public Attempts getAttemptByUserId(Long id) {
        return attemptsRepo.findByUserId(id);
    }
}
