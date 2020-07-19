package com.hackday.angelhack.user;

import com.hackday.angelhack.common.constant.SecurityConst;
import com.hackday.angelhack.util.JWTUtil;
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

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> doLogin(@ApiParam(value = "name은 필요하지 않습니다.") @RequestBody UserAuthDTO userAccount) {

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userAccount.getEmail(), userAccount.getPw());
        Authentication authentication = authenticationManager.authenticate(auth);

        if (authentication.isAuthenticated() == false) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserProfile loginedUser = userRepository.findByEmail(userAccount.getEmail());

        final String token = SecurityConst.TOKEN_PREFIX.concat(JWTUtil.encodeJWT(authentication.getName()));

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("email", loginedUser.getEmail());
        map.put("name", loginedUser.getName());

        return new ResponseEntity<>(map, HttpStatus.OK);
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
