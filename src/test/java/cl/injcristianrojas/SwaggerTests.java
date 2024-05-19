package cl.injcristianrojas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SwaggerTests {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testSwaggerUI() throws Exception {
    mvc.perform(get("/swagger-ui/index.html"))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void testSwaggerJSON() throws Exception {
    mvc.perform(get("/api-docs"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.openapi", is("3.0.1")));
  }
}
