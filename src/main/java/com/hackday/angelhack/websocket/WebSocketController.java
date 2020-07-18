package com.hackday.angelhack.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @MessageMapping("/content")
    public String send(String message) {
        System.out.println("send!!");
        return message;
    }
}
