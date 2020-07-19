package com.hackday.angelhack.projecttool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackday.angelhack.workspace.Workspace;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProjectTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toolId;

    private String toolName;

    @Lob
    private String contents;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;
}
