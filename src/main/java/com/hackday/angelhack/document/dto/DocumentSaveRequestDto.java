package com.hackday.angelhack.document.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentSaveRequestDto {

    private String title;
    private Long workspaceId;
}
