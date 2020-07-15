package com.hackday.angelhack.workspace;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackday.angelhack.domain.Workspace;
import com.hackday.angelhack.security.SecurityConst;
import com.hackday.angelhack.workspace.dto.WorkspaceSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping()
    public ResponseEntity<Map<String, List<Workspace>>> getWorkspaces(HttpServletRequest request) {
        List<Workspace> workspaces = workspaceService.findAllByUserId(request);
        Map<String, List<Workspace>> resposne = new LinkedHashMap<>();
        resposne.put("workspaceList", workspaces);
        return new ResponseEntity<>(resposne, HttpStatus.OK);
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
    public ResponseEntity delete(@PathVariable int workspaceId) {
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

}
