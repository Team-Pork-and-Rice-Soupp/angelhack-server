package com.hackday.angelhack.websocket;

import com.hackday.angelhack.document.Document;
import com.hackday.angelhack.meetinglog.MeetingLog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Room {

    private String meetingLogId;
    private int connection;
    private Document document;


    @Builder
    public Room(String meetingLogId, int connection, Document document) {
        this.meetingLogId = meetingLogId;
        this.connection = connection;
        this.document = document;
    }
}
