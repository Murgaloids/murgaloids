package com.murgaloids.server.conversation;

import java.util.List;

import com.murgaloids.server.JsonWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/conversations")
public class ConversationController {
  @Autowired
  private ConversationRepository conversationRepository;

  @PostMapping("/add")
  public JsonWrapper<Conversation> addConversation(@RequestBody Conversation conversation) {
    if (!conversationRepository.existsById(conversation.getId())) {
      conversationRepository.save(conversation);
      return new JsonWrapper<>(conversation);
    }

    return null;
  }

  @GetMapping("/exists")
  public JsonWrapper<Boolean> doesConversationExists(@RequestParam String id) {
    return new JsonWrapper<>(conversationRepository.existsById(id));
  }

  @GetMapping("/get-conversations")
  public JsonWrapper<List<Conversation>> getConversations(@RequestParam String id) {
    return new JsonWrapper<>(conversationRepository.findByUserId(id));
  }
}