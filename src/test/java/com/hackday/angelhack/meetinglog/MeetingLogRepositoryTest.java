package com.hackday.angelhack.meetinglog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class MeetingLogRepositoryTest {

    @Autowired
    private MeetingLogRepository meetingLogRepository;

    @Test
    public void getMeetingLogByUserEmailTest() {
        // given
        String userEmail = "root";

        // when
        MeetingLog meetingLog = meetingLogRepository.findByUserProfileEmail(userEmail);

        // then
        assertEquals(meetingLog.getContent(), "hello world");
    }

    @Test
    public void getMeetingLogByUserEmailAndWorkspaceId() {
        // given
        String userEmail = "root";
        Long workspaceId = 1L;

        // when
        MeetingLog meetingLog = meetingLogRepository.findByUserProfileEmailAndWorkspaceId(userEmail, workspaceId);

        // then
        assertEquals(meetingLog.getContent(), "hello world");
    }
}
