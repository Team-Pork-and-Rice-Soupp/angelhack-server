package com.hackday.angelhack.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.logging.Logger;

@Component
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketConnectionListener(SessionConnectedEvent event){

    }

    @EventListener
    public void handleWebSocketDisconnectionListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        System.out.println(String.format("[Disconnection] Session Id : %s", sessionId));
    }
}
