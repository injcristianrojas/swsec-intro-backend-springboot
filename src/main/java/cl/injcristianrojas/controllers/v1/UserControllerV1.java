package cl.injcristianrojas.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.model.UserJPA;
import cl.injcristianrojas.data.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserControllerV1 {

  @Autowired
  private UserRepository userRepo;

  @GetMapping("/users")
  public List<UserJPA> retrieveAllUsers() {
    return userRepo.findAll();
  }

  @GetMapping("/users/user/{username}")
  public List<UserJPA> retrieveUsersByUserName(@PathVariable String username) {
    return userRepo.getUsersByUsername(username);
  }

  @GetMapping("/users/type/{id}")
  public List<UserJPA> retrieveUsersByType(@PathVariable Long id) {
    return userRepo.getUsersByType(id);
  }

}
