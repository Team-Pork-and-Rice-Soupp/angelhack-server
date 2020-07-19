package com.hackday.angelhack.projecttool;

import com.hackday.angelhack.workspace.Workspace;
import com.hackday.angelhack.workspace.WorkspaceRepository;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProjectToolController {

    @Autowired
    private ProjectToolRepository projectToolRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @GetMapping("/api/workspace/{workspaceId}/tools/{toolId}")
    public ResponseEntity<String> getWorkspaceTool(@PathVariable Long workspaceId, @PathVariable Long toolId) {
        ProjectTool projectTool = projectToolRepository.findById(toolId).get();
        String contents = projectTool.getContents();
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @PostMapping("/api/workspace/{workspaceId}/tools")
    public ResponseEntity<String> addProjectTool(@PathVariable Long workspaceId, @ApiParam(value = "id는 입력하지 않습니다.") @RequestBody ProjectTool projectTool) {
        Workspace workspace = workspaceRepository.findById(workspaceId).get();
        projectTool.setToolId(null);
        projectTool.setWorkspace(workspace);

        try {
            projectToolRepository.save(projectTool);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/workspace/{workspaceId}/tools/{toolId}")
    public ResponseEntity<String> updateProjectTool(
            @PathVariable Long workspaceId,
            @PathVariable Long toolId,
            @ApiParam(value = "id는 json 이 아닌 경로로 부탁드립니다.") @RequestBody ProjectTool updatedProjectTool) {

        Workspace workspace = workspaceRepository.findById(workspaceId).get();
        ProjectTool projectTool = projectToolRepository.findById(toolId).get();
        projectTool.setWorkspace(workspace);
        projectTool.setContents(updatedProjectTool.getContents());

        try {
            projectToolRepository.save(projectTool);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/api/workspace/{workspaceId}/tools/{toolId}")
    public ResponseEntity<String> removeProjectTool(@PathVariable Long workspaceId, @PathVariable Long toolId) {
        ProjectTool projectTool = projectToolRepository.findById(toolId).get();
        projectToolRepository.delete(projectTool);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
