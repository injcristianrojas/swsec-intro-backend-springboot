package cl.injcristianrojas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiV1Tests {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testMessageV1() throws Exception {
    mvc.perform(get("/api/v1/messages"))
        .andExpect(status().is2xxSuccessful());
  }

}
