package com.semzy.myquizapp.service;

import com.semzy.myquizapp.dao.AttemptsRepo;
import com.semzy.myquizapp.entity.Attempts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptsService {

    @Autowired
    private AttemptsRepo attemptsRepo;

    public void saveAttempt(Attempts attempts) {

        attemptsRepo.save(attempts);

    }

    public Attempts getAttemptByUserId(int id) {
        return attemptsRepo.findByUserId(id);
    }
}
