package com.semzy.myquizapp.dao;

import com.semzy.myquizapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

     Users findByUsername(String username);
    Boolean existsByUsername(String username);
}
