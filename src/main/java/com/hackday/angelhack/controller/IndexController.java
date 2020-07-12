package com.hackday.angelhack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController {

    @PostMapping("/signup")
    public ResponseEntity createUser(){
        return new ResponseEntity("Create User", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return new ResponseEntity("Success login", HttpStatus.CREATED);
    }

}
