package com.hackday.angelhack.assessment.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AssessmentMemberDto {

    private String email;
    private List<Integer> scores;

    public AssessmentMemberDto(String email) {
        this.email = email;
        this.scores = new ArrayList<>();
    }

    public void setScore(int score){
        scores.add(score);
    }
}
