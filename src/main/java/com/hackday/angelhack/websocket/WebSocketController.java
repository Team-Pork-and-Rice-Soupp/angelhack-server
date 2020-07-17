package com.hackday.angelhack.websocket;

import com.hackday.angelhack.common.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/websocket/endpoint")
    @SendTo("/websocket/subscribe")
    public Message send(Message msg) {
        return msg;
    }
}
