package com.hackday.angelhack.websocket;

import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private RoomRepository roomRepository;

    public Room enterRoom(String meetingLogId){
        return roomRepository.joinRoom(meetingLogId);
    }
}
