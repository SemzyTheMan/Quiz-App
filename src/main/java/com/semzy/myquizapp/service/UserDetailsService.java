package com.semzy.myquizapp.service;

import com.semzy.myquizapp.dao.UserRepo;
import com.semzy.myquizapp.entity.UserPrincipal;
import com.semzy.myquizapp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


        @Autowired
        private UserRepo userRepo;


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Users user = userRepo.findByUsername(username);
            if (user == null) {
                System.out.println("User Not Found");
                throw new UsernameNotFoundException("user not found");
            }

            return new UserPrincipal(user);
        }

}
