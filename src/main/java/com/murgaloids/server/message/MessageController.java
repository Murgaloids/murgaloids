package com.murgaloids.server.message;

import java.util.List;

import com.murgaloids.server.JsonWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.murgaloids.server.conversation.ConversationRepository;

@RestController
@RequestMapping(path="/messages")
public class MessageController {
  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ConversationRepository conversationRepository;

  @PostMapping("add")
  public JsonWrapper<Message> addMessage(@RequestBody Message message) {
    if (conversationRepository.existsById(message.getConversationId())) {
      messageRepository.save(message);
      return new JsonWrapper<>(message);
    }

    return null;
  }

  @GetMapping("get-messages")
  public JsonWrapper<List<Message>> getMessages(@RequestParam String id) {
    if (conversationRepository.existsById(id)) {
      return new JsonWrapper<>(messageRepository.findByConversationId(id));
    }

    return null;
  }
}