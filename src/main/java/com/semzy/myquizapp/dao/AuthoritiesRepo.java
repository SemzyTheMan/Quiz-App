package com.semzy.myquizapp.dao;

import com.semzy.myquizapp.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepo extends JpaRepository<Authorities,Integer> {
    Authorities findByName(String name);
}
