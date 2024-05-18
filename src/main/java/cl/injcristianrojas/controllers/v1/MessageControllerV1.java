package cl.injcristianrojas.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.model.Message;
import cl.injcristianrojas.data.repositories.MessageRepository;

@RestController
@RequestMapping("/api/v1")
public class MessageControllerV1 {

  @Autowired
  private MessageRepository messageRepo;

  @GetMapping("/messages")
  public List<Message> retrieveAllMessages() {
    return messageRepo.findAll();
  }

  @PostMapping("/messages/add")
  public Message newPost(@RequestBody Message newPost) {
    return messageRepo.save(newPost);
  }

}
