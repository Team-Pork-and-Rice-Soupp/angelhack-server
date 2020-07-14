package com.hackday.angelhack.workspace.dto;

import com.hackday.angelhack.domain.PROJECT_ROLE;
import com.hackday.angelhack.domain.Workspace;
import com.hackday.angelhack.domain.WorkspaceUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {

    private String email;
    private String description;
    private PROJECT_ROLE role;

    @Builder
    public MemberSaveRequestDto(String email, String description, PROJECT_ROLE role) {
        this.email = email;
        this.description = description;
        this.role = role;
    }

    public WorkspaceUser toEntity(){
        return WorkspaceUser.builder()
                .description(this.description)
                .role(this.role)
                .email(email)
                .build();
    }
}
