package cl.injcristianrojas.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.jpa.model.MessageJPA;
import cl.injcristianrojas.data.jpa.model.repositories.MessageRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private MessageRepository messageRepo;

  @GetMapping("/messages")
  public List<MessageJPA> retrieveAllMessages() {
    return messageRepo.findAll();
}

}
