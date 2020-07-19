package com.hackday.angelhack.projecttool;

import com.hackday.angelhack.workspace.Workspace;
import com.hackday.angelhack.workspace.WorkspaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectToolTest {

    @Autowired
    private ProjectToolRepository projectToolRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Test
    public void addProjectToolTest() {
        // given
        Long workspaceId = 1L;
        Workspace workspace = workspaceRepository.findById(workspaceId).get();

        ProjectTool projectTool = new ProjectTool();
        projectTool.setWorkspace(workspace);
        projectTool.setContents("{'id':'1'}");
        projectTool.setToolName("SWOT");

        // when
        projectToolRepository.save(projectTool);
    }

}
