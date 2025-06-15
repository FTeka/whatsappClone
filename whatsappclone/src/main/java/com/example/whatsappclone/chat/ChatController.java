package com.example.whatsappclone.chat;

import com.example.whatsappclone.common.StringResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping()
    public ResponseEntity<StringResponse> createChat(
            @RequestParam(name = "sender-id " )String senderId,
            @RequestParam(name = "receiver-id") String receiverId
    ){
        final String chatId= chatService.createChat(senderId,receiverId);
        StringResponse response= StringResponse.builder()
                .response(chatId).build();
        return ResponseEntity.ok(response);

    }
    @GetMapping
    public ResponseEntity<List<ChatResponse>> getCatsByReceiver(Authentication authentication){
        return ResponseEntity.ok(chatService.getChatsByReceiverId(authentication));
    }
}
