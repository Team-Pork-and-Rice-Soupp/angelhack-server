package com.hackday.angelhack.workspace.dto;

import com.hackday.angelhack.domain.Workspace;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
        return Workspace.builder()
                .title(this.title)
                .description(this.description)
                .workspaceUsers(this.members.stream()
                        .map(WorkspaceUserSaveRequestDto::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
