package cl.injcristianrojas.controllers.v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.model.UserJPA;
import cl.injcristianrojas.data.repositories.UserRepository;
import cl.injcristianrojas.security.JwtService;

@RestController
@RequestMapping("/api/v2")
public class UserControllerV2 {

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public Map<String, Object> authenticateAndGetToken(@RequestBody UserJPA authRequest) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    if (authentication.isAuthenticated()) {
      Map<String, Object> map = new HashMap<>();
      map.put("token", jwtService.generateToken(authRequest.getUsername()));
      return map;
    } else
      throw new UsernameNotFoundException("invalid user request!");
  }

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
