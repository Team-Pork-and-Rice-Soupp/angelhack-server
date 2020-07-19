package com.hackday.angelhack.assessment;

import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.user.UserRepository;
import com.hackday.angelhack.workspace.Workspace;
import com.hackday.angelhack.workspace.WorkspaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AssessmentRepositoryTest {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Test
    public void addAssessmentTest() {
        // given
        String estimatorEmail = "root";
        String evaluatedUserEmail = "hahava@naver.com";

        UserProfile estimator = userRepository.findByEmail(estimatorEmail);
        UserProfile evaluatedUser = userRepository.findByEmail(evaluatedUserEmail);

        Workspace workspace = workspaceRepository.findById(1L).get();

        Assessment assessment = new Assessment();
        assessment.setScore(100);
        assessment.setEstimator(estimator);
        assessment.setEvaluatedUser(evaluatedUser);
        assessment.setWorkspace(workspace);
        assessment.setStep("2");

        // when
        assessmentRepository.save(assessment);
    }

    @Test
    public void getScoreByUserListsTest() {

        // given
        Long workspaceId = 1L;

        List<Assessment> assessments = assessmentRepository.findAllByWorkspaceId(workspaceId);

        // TODO

    }

}
