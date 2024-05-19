package cl.injcristianrojas.controllers.v2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.injcristianrojas.data.model.UserJPA;
import cl.injcristianrojas.security.jwt.JwtService;

@RestController
@RequestMapping("/api/v2")
public class LoginControllerV2 {

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
      map.put("token", jwtService.generateToken(authentication));
      return map;
    } else
      throw new UsernameNotFoundException("invalid user request!");
  }

}
