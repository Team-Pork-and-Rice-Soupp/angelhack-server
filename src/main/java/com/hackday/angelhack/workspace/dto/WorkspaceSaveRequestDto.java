package com.hackday.angelhack.workspace.dto;

import com.hackday.angelhack.workspace.Workspace;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WorkspaceSaveRequestDto {

    private String title;
    private String description;
    private List<WorkspaceUserSaveRequestDto> members;

    @Builder
    public WorkspaceSaveRequestDto(String title, String description, List<WorkspaceUserSaveRequestDto> members) {
        this.title = title;
        this.description = description;
        this.members = members;
    }

    public Workspace toEntity() {
        Workspace workspace = new Workspace();
        workspace.setDescription(description);
        workspace.setTitle(title);

        return workspace;
    }
}
