package com.hackday.angelhack.assessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentMemberSaveRequestDto {

    private String email;
    private int score;
}
