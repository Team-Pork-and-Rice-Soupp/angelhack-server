package com.hackday.angelhack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IndexController {

    @PostMapping("/signup")
    public ResponseEntity createUser() {
        return new ResponseEntity("Create User", HttpStatus.CREATED);
    }

    @GetMapping("/user/search")
    public ResponseEntity getUsers(@RequestParam("keyword") String keyword) {
        return new ResponseEntity("Success", HttpStatus.OK);
    }


}
