package com.hackday.angelhack.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackday.angelhack.common.constant.ProjectRole;
import com.hackday.angelhack.user.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Setter
@Table(name = "workspace_user")
public class WorkspaceUser {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile user;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectRole role;
}
