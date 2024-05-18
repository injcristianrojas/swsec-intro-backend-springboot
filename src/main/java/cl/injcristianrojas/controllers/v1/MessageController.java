package cl.injcristianrojas.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.model.MessageJPA;
import cl.injcristianrojas.data.repositories.MessageRepository;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

  @Autowired
  private MessageRepository messageRepo;

  @GetMapping("/messages")
  public List<MessageJPA> retrieveAllMessages() {
    return messageRepo.findAll();
  }

  @PostMapping("/messages/add")
  public MessageJPA newPost(@RequestBody MessageJPA newPost) {
    return messageRepo.save(newPost);
  }

}