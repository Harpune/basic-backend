package com.example.sandbox.config;

import com.example.sandbox.service.JwtService;
import com.example.sandbox.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SecurityConfig.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    PersonaService personaService;

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void handlePersonaNotFoundException() throws Exception {
        mvc.perform(post("/api/v1/personas"))
                .andExpect(status().isOk());
        // http://localhost:8080/api/v1/auth/signup
    }

    @Test
    void handleMethodArgumentNotValid() {
    }

    @Test
    void processRuntimeException() {
    }

    @Test
    void handleInternalAuthenticationServiceException() {
    }

    @Test
    void handleAuthenticationException() {
    }
}