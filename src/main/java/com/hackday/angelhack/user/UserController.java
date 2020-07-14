package com.hackday.angelhack.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/search")
    public ResponseEntity getUsers(@RequestParam("keyword") String keyword){
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
