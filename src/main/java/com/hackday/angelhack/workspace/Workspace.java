package com.hackday.angelhack.workspace;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hackday.angelhack.common.domain.BaseTimeEntity;
import com.hackday.angelhack.document.Document;
import com.hackday.angelhack.projecttool.ProjectTool;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name = "workspace")
public class Workspace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @JsonProperty("members")
    @OneToMany(mappedBy = "workspace", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<WorkspaceUser> workspaceUsers;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE)
    private List<ProjectTool> tools;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE)
    private List<Document> documents;
}
