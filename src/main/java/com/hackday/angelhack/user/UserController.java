package com.hackday.angelhack.user;

import com.hackday.angelhack.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> getUsers(@RequestParam("keyword") String keyword) {
        List<UserResponseDto> users = userService.findUserByKeyword(keyword);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
