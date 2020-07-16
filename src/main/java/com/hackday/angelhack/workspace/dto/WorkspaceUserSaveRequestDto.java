package com.hackday.angelhack.workspace.dto;

import com.hackday.angelhack.common.constant.ProjectRole;
import com.hackday.angelhack.workspace.Workspace;
import com.hackday.angelhack.workspace.WorkspaceUser;
import com.hackday.angelhack.user.UserProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkspaceUserSaveRequestDto {

    private String email;
    private String description;
    private ProjectRole role;

    @Builder
    public WorkspaceUserSaveRequestDto(String email, String description, ProjectRole role) {
        this.email = email;
        this.description = description;
        this.role = role;
    }

    public WorkspaceUser toEntity(UserProfile user, Workspace workspace) {
        WorkspaceUser entity = new WorkspaceUser();
        entity.setDescription(description);
        entity.setRole(role);
        entity.setUser(user);
        entity.setWorkspace(workspace);

        return entity;
    }
}
