package com.example.taskManagement;
import com.example.taskManagement.controllers.userController;
import com.example.taskManagement.models.userDto;
import com.example.taskManagement.services.userService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(userController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private userService userService;

    @Test
    public void testUserRegistration() throws Exception {
        // Mock user registration request
        userDto userDTO = new userDto();
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPassword");

        // Mock user registration response
        Mockito.when(userService.registerUser(Mockito.any(userDto.class)))
               .thenReturn(true);

        // Perform the request and assert the response
        mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testUserLogin() throws Exception {
        // Mock user login request
    	userDto loginDTO = new userDto();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");

        // Mock user login response
        // Assuming you have a custom authentication success handler
        Mockito.when(userService.userLogin(Mockito.any(userDto.class)))
               .thenReturn(true);

        // Perform the request and assert the response
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful!"));
    }

    // Utility method to convert objects to JSON format
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
