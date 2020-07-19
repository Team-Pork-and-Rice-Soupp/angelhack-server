package com.hackday.angelhack.assessment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssessmentSaveRequestDto {

    private String step;
    private List<AssessmentMemberSaveRequestDto> members;
}
