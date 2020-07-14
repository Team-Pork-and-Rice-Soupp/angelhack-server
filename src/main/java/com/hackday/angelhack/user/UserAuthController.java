package com.hackday.angelhack.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> doLogin(@RequestBody UserAuth userAuth) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userAuth.getEmail(), userAuth.getPw());
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            String jwt = JWT.create().withSubject(authentication.getName()).withExpiresAt(Date.valueOf(LocalDate.now().plusDays(2))).sign(Algorithm.HMAC512("secret"));
            return new ResponseEntity<>("token"+jwt, HttpStatus.OK);
        }
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
