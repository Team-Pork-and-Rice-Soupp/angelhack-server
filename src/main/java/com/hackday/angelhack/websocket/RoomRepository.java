package com.hackday.angelhack.websocket;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RoomRepository {

    private Map<String, Room> roomMap;

    @PostConstruct
    private void init() {
        roomMap = new HashMap<>();
    }

    public Room joinRoom(String meetingLogId) {
        if (roomMap.containsKey(meetingLogId)) {
            return roomMap.get(meetingLogId);
        }

        Room newRoom = Room.builder()
                .meetingLogId(meetingLogId)
                .connection(1)
                .build();

        roomMap.put(meetingLogId, newRoom);

        return newRoom;
    }
}
