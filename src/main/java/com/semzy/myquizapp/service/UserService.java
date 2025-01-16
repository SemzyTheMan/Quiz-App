package com.semzy.myquizapp.service;

import com.semzy.myquizapp.dao.AuthoritiesRepo;
import com.semzy.myquizapp.dao.UserRepo;
import com.semzy.myquizapp.entity.Authorities;
import com.semzy.myquizapp.entity.AuthorityEnum;
import com.semzy.myquizapp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthoritiesRepo authoritiesRepo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional
    public Users register(Users user) {
        try {
            Authorities userAuthority = authoritiesRepo.findByName(AuthorityEnum.USER.toString());

            user.setPassword(encoder.encode(user.getPassword()));
            user.setEnabled(1);
            Users saved = repo.save(user);
            saved.addRoles(userAuthority);
            return repo.save(saved);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public Users findByUsername(String username){
        return  repo.findByUsername(username);
    }
}
