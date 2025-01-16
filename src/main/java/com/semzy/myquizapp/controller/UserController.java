package com.semzy.myquizapp.controller;

import com.semzy.myquizapp.dao.UserRepo;
import com.semzy.myquizapp.entity.ErrorResponse;
import com.semzy.myquizapp.entity.LoginDTO;
import com.semzy.myquizapp.entity.Users;
import com.semzy.myquizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        System.out.println("I am being hit");
        if(userRepo.existsByUsername(user.getUsername())){
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Registration Error",
                    "Username is already taken!",
                    "/users/register"
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        Users savedUser = service.register(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Get the authenticated user's details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // Fetch the User object from the database (using your UserRepository)
            Users user = userRepo.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            int userId = user.getId();

            Map<String, Object> response = new HashMap<>();
            response.put("userId", userId);
            response.put("username", username);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Authentication Error",
                    "Invalid username or password",
                    "/users/signin"
            );
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}

