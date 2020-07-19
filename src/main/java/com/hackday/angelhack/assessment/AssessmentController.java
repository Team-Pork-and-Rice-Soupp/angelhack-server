package com.hackday.angelhack.assessment;

import com.hackday.angelhack.assessment.dto.AssessmentSaveRequestDto;
import com.hackday.angelhack.common.constant.SecurityConst;
import com.hackday.angelhack.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PostMapping("/api/workspace/assessment/{workspaceId}")
    public ResponseEntity<String> addAssessment(
            HttpServletRequest request,
            @RequestBody AssessmentSaveRequestDto dto,
            @PathVariable Long workspaceId){

        String email = JWTUtil.decodeJWT(request.getHeader(SecurityConst.HEADER_STRING));
        assessmentService.createAssessment(dto, email, workspaceId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
