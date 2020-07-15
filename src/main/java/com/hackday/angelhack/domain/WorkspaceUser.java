package com.hackday.angelhack.domain;

import com.hackday.angelhack.user.UserAuth;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Setter
@Table(name = "workspace_user")
public class WorkspaceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name = "USER_AUTH_id")
    private UserAuth user;

    private String description;

    @Enumerated(EnumType.STRING)
    private PROJECT_ROLE role;
}
