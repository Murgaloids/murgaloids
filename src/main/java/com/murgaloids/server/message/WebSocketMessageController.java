package com.murgaloids.server.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;

import com.murgaloids.server.conversation.ConversationRepository;

@Controller
public class WebSocketMessageController {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ConversationRepository conversationRepository;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/chats/{conversationId}")
  public void onReceiveMessage(@Payload Message message, @DestinationVariable("conversationId") String conversationId, MessageHeaders messageHeaders) {
    if (conversationRepository.existsById(message.getConversationId())) {
      messageRepository.save(message);
      simpMessagingTemplate.convertAndSend("/topic/chat." + conversationId, message);
    }
  }

}