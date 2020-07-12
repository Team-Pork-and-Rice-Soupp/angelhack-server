package com.hackday.angelhack.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    @GetMapping("/{userId}")
    public ResponseEntity getWorkspaces(@PathVariable int userId) {
        return new ResponseEntity(userId, HttpStatus.OK);
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
