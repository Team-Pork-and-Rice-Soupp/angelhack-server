package com.hackday.angelhack.domain;

import com.hackday.angelhack.user.UserAuth;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "workspace_user")
public class MemberSaveRequestDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="workspace_id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name="user_auth_id")
    private UserAuth user;

    private String description;

    private PROJECT_ROLE role;
}
