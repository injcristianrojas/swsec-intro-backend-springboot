package cl.injcristianrojas.controllers;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HealthCheckController {

  @GetMapping(value = { "/healthcheck/{filename}", "/healthcheck" })
  public ResponseEntity<String> healthCheck(@PathVariable Optional<String> filename) {
    String file = filename.isPresent() ? filename.get() : "healthcheck";
    String command = "cat " + file;
    System.out.println(command);
    StringBuilder output = new StringBuilder();
    try {
      Process process = Runtime.getRuntime().exec(command);
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          output.append(line).append("\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(output.toString(), HttpStatus.OK);
  }

}
