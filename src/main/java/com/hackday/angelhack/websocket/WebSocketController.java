package com.hackday.angelhack.websocket;

import com.hackday.angelhack.document.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebSocketController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final WebSocketService webSocketService;
    private final DocumentService documentService;

    @MessageMapping("/content")
    public void send(Message message) {
        documentService.receiveMessage(message);
        messagingTemplate.convertAndSend("/websocket/subscribe/" + message.getDocumentId(), message);
    }

    @MessageMapping("/create")
    public Room enterDocumentRoom(Message message){
        return webSocketService.enterRoom(message.getDocumentId());
    }
}
