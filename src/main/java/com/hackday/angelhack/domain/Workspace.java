package com.hackday.angelhack.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "workspace")
public class Workspace extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "workspace", fetch = FetchType.EAGER)
    private List<WorkspaceUser> workspaceUsers;

    @Builder
    public Workspace(String title, String description, List<WorkspaceUser> workspaceUsers) {
        this.title = title;
        this.description = description;
        this.workspaceUsers = workspaceUsers;
    }
}
