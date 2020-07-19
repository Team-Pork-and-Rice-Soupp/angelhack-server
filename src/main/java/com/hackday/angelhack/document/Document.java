package com.hackday.angelhack.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackday.angelhack.common.domain.BaseTimeEntity;
import com.hackday.angelhack.workspace.Workspace;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="document")
public class Document extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;
}
