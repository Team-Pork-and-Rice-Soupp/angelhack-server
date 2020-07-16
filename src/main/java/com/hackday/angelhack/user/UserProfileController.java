package com.hackday.angelhack.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackday.angelhack.security.SecurityConst;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
public class UserProfileController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> doLogin(
            @ApiParam(value = "name은 필요하지 않습니다.")
            @RequestBody UserAuthDTO userAccount) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userAccount.getEmail(), userAccount.getPw());
        Authentication authentication = authenticationManager.authenticate(token);

        if (authentication.isAuthenticated() == false) {
            return new ResponseEntity<>("email or password invalid", HttpStatus.BAD_REQUEST);
        }

        final String jwt = JWT
                .create()
                .withSubject(authentication.getName())
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(2)))
                .sign(Algorithm.HMAC512(SecurityConst.SECRET_KEY));

        return new ResponseEntity<>(SecurityConst.TOKEN_PREFIX + jwt, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> doSignup(@RequestBody UserAuthDTO userInfo) {
        UserProfile userProfile = UserProfile.of(userInfo);
        userProfile.setPw(passwordEncoder.encode(userProfile.getPw()));
        try {
            userRepository.save(userProfile);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
