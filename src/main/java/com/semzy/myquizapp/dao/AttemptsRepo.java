package com.semzy.myquizapp.dao;

import com.semzy.myquizapp.entity.Attempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptsRepo extends JpaRepository<Attempts,Integer> {

   Attempts findByUserId(Long id);
}
