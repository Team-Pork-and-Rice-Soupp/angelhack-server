package com.hackday.angelhack.meetinglog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingLogRepository extends CrudRepository<MeetingLog, Long> {

    MeetingLog findByUserProfileEmail(String userEmail);

    MeetingLog findByWorkspaceId(Long workspaceId);

    MeetingLog findByUserProfileEmailAndWorkspaceId(String userEmail, Long workspaceId);

    MeetingLog findByIdAndUserProfileEmail(Long logId, String userEmail);

    void deleteByIdAndUserProfileEmail(Long meetingLodId, String userEmail);

    List<MeetingLog> findAllByUserProfileEmailAndWorkspaceId(String userEmail, Long workspaceId);
}
