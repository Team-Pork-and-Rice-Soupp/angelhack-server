package com.hackday.angelhack.user.dto;

import com.hackday.angelhack.user.UserAuth;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String email;

    public UserResponseDto(UserAuth entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
    }
}
