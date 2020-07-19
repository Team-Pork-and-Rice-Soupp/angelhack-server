package com.hackday.angelhack.assessment;

import com.hackday.angelhack.assessment.dto.AssessmentMemberSaveRequestDto;
import com.hackday.angelhack.assessment.dto.AssessmentSaveRequestDto;
import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.user.UserRepository;
import com.hackday.angelhack.workspace.Workspace;
import com.hackday.angelhack.workspace.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;

    public void createAssessment(AssessmentSaveRequestDto dto, String estimatorEmail, Long workspaceId){
        List<AssessmentMemberSaveRequestDto> members = dto.getMembers();
        UserProfile estimator = userRepository.findByEmail(estimatorEmail);
        Workspace workspace = workspaceRepository.findById(workspaceId).get();

        for(AssessmentMemberSaveRequestDto member : members){
            UserProfile evaluator = userRepository.findByEmail(member.getEmail());
            Assessment assessment = new Assessment();
            assessment.setStep(dto.getStep());
            assessment.setScore(member.getScore());
            assessment.setEstimator(estimator);
            assessment.setEvaluatedUser(evaluator);
            assessment.setWorkspace(workspace);

            assessmentRepository.save(assessment);
        }
    }
}
