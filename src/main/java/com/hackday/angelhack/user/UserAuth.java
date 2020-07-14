package com.hackday.angelhack.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USER_AUTH")
public class UserAuth {
    @Id
    @ApiParam(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiParam(value = "사용자의 이메일", required = true)
    private String email;

    @ApiParam(value = "사용자의 패스워드", required = true)
    @JsonIgnore
    private String pw;

    @ApiParam(hidden = true)
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private ROLE role;
}
