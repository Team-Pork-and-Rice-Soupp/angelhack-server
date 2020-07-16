package com.hackday.angelhack.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackday.angelhack.domain.WorkspaceUser;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER_AUTH")
public class UserAuth {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    @JsonIgnore
    private String pw;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private ROLE role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WorkspaceUser> workspaceUsers;
}
