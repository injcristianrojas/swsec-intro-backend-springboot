package cl.injcristianrojas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiV2Tests {

  @Autowired
  private MockMvc mockMvc;

  private String token;

  @BeforeEach
  private void getToken() throws Exception, UnsupportedEncodingException {
    String loginPayload = "{\"username\":\"chincol\",\"password\":\"fiofio\"}";
    MvcResult loginResult = mockMvc.perform(post("/api/v2/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(loginPayload))
        .andExpect(status().isOk())
        .andReturn();
    String responseContent = loginResult.getResponse().getContentAsString();
    token = JsonPath.parse(responseContent).read("$.token");
  }


  @Test
  public void testAuthenticatedRequest() throws Exception {
    mockMvc.perform(get("/api/v2/messages")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }

}
