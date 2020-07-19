package com.hackday.angelhack.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthDTO {
    private String email;
    private String pw;
    private String name;
}
