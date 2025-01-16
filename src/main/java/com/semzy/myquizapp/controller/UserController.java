package com.semzy.myquizapp.controller;

import com.semzy.myquizapp.dao.UserRepo;
import com.semzy.myquizapp.dtos.SignInResponse;
import com.semzy.myquizapp.entity.ErrorResponse;
import com.semzy.myquizapp.entity.LoginDTO;
import com.semzy.myquizapp.entity.Users;
import com.semzy.myquizapp.service.JwtService;
import com.semzy.myquizapp.service.UserDetailsService;
import com.semzy.myquizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {

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
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            final Users user = service.findByUsername(loginDto.getUsername());
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
            final String jwt = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new SignInResponse(user.getId(),jwt));

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

