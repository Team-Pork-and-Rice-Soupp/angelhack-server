package com.hackday.angelhack.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<Map<String, List<UserAuth>>> getUsers(@RequestParam("keyword") String keyword) {
        List<UserAuth> users = userService.findUserByKeyword(keyword);
        Map<String , List<UserAuth>> response = new LinkedHashMap<>();
        response.put("userList", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
