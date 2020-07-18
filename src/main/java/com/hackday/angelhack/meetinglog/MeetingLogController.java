package com.hackday.angelhack.meetinglog;

import com.hackday.angelhack.common.constant.SecurityConst;
import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.user.UserRepository;
import com.hackday.angelhack.util.JWTUtil;
import com.hackday.angelhack.workspace.Workspace;
import com.hackday.angelhack.workspace.WorkspaceRepository;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class MeetingLogController {

    @Autowired
    private MeetingLogRepository meetingLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @PostMapping("/api/workspace/{workspaceId}/log")
    public ResponseEntity<String> addMeetingLog(@PathVariable Long workspaceId,
                                                @ApiParam(value = "id는 입력하지 않습니다.") @RequestBody MeetingLog meetingLog,
                                                HttpServletRequest request) {

        String token = request.getHeader(SecurityConst.HEADER_STRING);
        String userEmail = JWTUtil.decodeJWT(token);
        UserProfile userProfile = userRepository.findByEmail(userEmail);
        Workspace workspace = workspaceRepository.findById(workspaceId).get();

        meetingLog.setId(null);
        meetingLog.setUserProfile(userProfile);
        meetingLog.setWorkspace(workspace);

        try {
            meetingLogRepository.save(meetingLog);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/workspace/{workspaceId}/log")
    public ResponseEntity<String> updateMeetingLog(@PathVariable Long workspaceId,
                                                   @RequestBody MeetingLog meetingLog,
                                                   HttpServletRequest request) {

        String token = request.getHeader(SecurityConst.HEADER_STRING);
        String userEmail = JWTUtil.decodeJWT(token);
        MeetingLog originLog = meetingLogRepository.findByIdAndUserProfileEmail(meetingLog.getId(), userEmail);

        originLog.setTitle(meetingLog.getTitle());
        originLog.setContent(meetingLog.getContent());

        try {
            meetingLogRepository.save(originLog);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/api/workspace/{workspaceId}/log/{logId}")
    public ResponseEntity<String> removeMeetingLog(@PathVariable Long workspaceId, @PathVariable Long logId, HttpServletRequest request) {
        String token = request.getHeader(SecurityConst.HEADER_STRING);
        String userEmail = JWTUtil.decodeJWT(token);
        try {
            meetingLogRepository.deleteByIdAndUserProfileEmail(logId, userEmail);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/workspaces/{workspaceId}/logs")
    public ResponseEntity<List<MeetingLog>> getLogs(@PathVariable Long workspaceId, HttpServletRequest request) {
        String token = request.getHeader(SecurityConst.HEADER_STRING);
        String userEmail = JWTUtil.decodeJWT(token);

        List<MeetingLog> meetingLogs = meetingLogRepository.findAllByUserProfileEmailAndWorkspaceId(userEmail, workspaceId);

        return new ResponseEntity<>(meetingLogs, HttpStatus.OK);
    }


}
