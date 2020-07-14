package com.hackday.angelhack.workspace.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WorkspaceSaveRequestDto {

    private String title;
    private String description;
    private List<WorkspaceUser> members;

    @Builder
    public WorkspaceSaveRequestDto(String title, String description, List<WorkspaceUser> members) {
        this.title = title;
        this.description = description;
        this.members = members;
    }
}
