package com.hackday.angelhack.workspace;

import com.hackday.angelhack.workspace.dto.WorkspaceResponseDto;
import com.hackday.angelhack.workspace.dto.WorkspaceSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping()
    public ResponseEntity<List<WorkspaceResponseDto>> getWorkspaces(HttpServletRequest request) {
        List<WorkspaceResponseDto> workspaces = workspaceService.findAllByUserId(request);
        return new ResponseEntity<>(workspaces, HttpStatus.OK);
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<Workspace> getWorkspace(HttpServletRequest request, @PathVariable Long workspaceId) {
        Workspace workspace;

        try {
            workspace = workspaceService.getWorkspaceInfoByWorkspaceId(request, workspaceId);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResponseEntity("", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(workspace, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody WorkspaceSaveRequestDto requestDto) {
        Long result = workspaceService.save(requestDto);
        if (result == null) {
            return new ResponseEntity<>("Failed to create workspace", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable Long workspaceId) {
        Long result = workspaceService.deleteById(request, workspaceId);

        if(result == 0L){
            return new ResponseEntity<>("User와 WorkspaceId가 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
