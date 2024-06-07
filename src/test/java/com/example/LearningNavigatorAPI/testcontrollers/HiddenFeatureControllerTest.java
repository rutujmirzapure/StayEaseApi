package com.example.LearningNavigatorAPI.testcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.LearningNavigatorAPI.controllers.HiddenFeatureController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HiddenFeatureController.class)
public class HiddenFeatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetNumberTrivia_Success() throws Exception {
        mockMvc.perform(get("/hidden-feature/42/trivia"))
                .andExpect(status().isOk()); 
    }

    // You can add more tests to check for specific content in the response
    // or to test how your controller handles errors from the Numbers API.
}