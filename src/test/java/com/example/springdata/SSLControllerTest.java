package com.example.springdata;

import com.example.controller.SSLController;
import com.example.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SSLController.class)
public class SSLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloWorldController() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/ssl"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_MARKDOWN));
                //.andExpect(content().string("Hello World !!"));
    }

    @Test
    public void testClientSSL() {
        //before add cert to java carcert file
        String fooResourceUrl
                = "https://127.0.0.1:8001/person";
        RestTemplate restTemplate = new RestTemplate();

       // HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
       // assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));

        ResponseEntity<Person> response
                = restTemplate.getForEntity(fooResourceUrl , Person.class);
        System.out.println(response.getBody());
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
