package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping("/{userId}")
    public ResponseEntity getWorkspaces(@PathVariable Long userId) {

        List<Workspace> workspaces = workspaceService.findAllByUserId(userId);
        return new ResponseEntity(workspaces, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{workspaceId}")
    public ResponseEntity getWorkspace(@PathVariable int userId, @PathVariable int workspaceId){
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity save(){
        return new ResponseEntity("Success", HttpStatus.CREATED);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity delete(@PathVariable int workspaceId){
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

}
