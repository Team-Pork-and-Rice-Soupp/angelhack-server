package com.hackday.angelhack.workspace.dto;

import com.hackday.angelhack.workspace.Workspace;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkspaceResponseDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public WorkspaceResponseDto(Workspace workspace){
        this.id = workspace.getId();
        this.title = workspace.getTitle();
        this.description = workspace.getDescription();
        this.createDate = workspace.getCreatedDate();
        this.updateDate = workspace.getModifiedDate();
    }
}
