package com.hackday.angelhack.domain;

import com.hackday.angelhack.user.UserAuth;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "workspace_user")
public class WorkspaceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="workspace_id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name="user_auth_email", referencedColumnName = "email")
    private UserAuth user;

    private String description;

    @Enumerated(EnumType.STRING)
    private PROJECT_ROLE role;

    @Builder
    public WorkspaceUser(String description, PROJECT_ROLE role, String email) {
        this.description = description;
        this.role = role;
        this.user.setEmail(email);
    }
}
