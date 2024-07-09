package com.xciting.victorpacheco.gamelauncher.infrastructure.adapters.inbound.rest;

import com.xciting.victorpacheco.gamelauncher.application.ports.inbound.CreateSessionUseCase;
import com.xciting.victorpacheco.gamelauncher.domain.exceptions.SessionCreationException;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionRequestDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GameLauncherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateSessionUseCase createSessionUseCase;

    @BeforeEach
    void setUp() {
        // setup code if needed
    }

    @Test
    void givenValidRequest_whenPlayGame_thenRedirectsToGameUrl() throws Exception {
        // given
        UUID sessionId = UUID.randomUUID();
        String redirectUrl = "https://games.xciting.com/testGame?session=" + sessionId + "&language=en";
        CreateSessionResponseDTO responseDTO = CreateSessionResponseDTO.builder()
                .sessionId(sessionId)
                .createdAt(null)
                .redirectUrl(redirectUrl)
                .build();

        when(createSessionUseCase.createSession(any(CreateSessionRequestDTO.class))).thenReturn(responseDTO);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // when & then
        mockMvc.perform(get("/play/testGame")
                .header("User-Agent", "Mozilla/5.0")
                .header("Referer", "http://example.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                //.andExpect(redirectedUrlPattern("https://games.xciting.com/testGame?session=*&language=en"));
                .andExpect(redirectedUrlPattern("https://games.xciting.com/testGame?session=*&language=en"));

        verify(createSessionUseCase).createSession(any(CreateSessionRequestDTO.class));
    }

    @Test
    void givenSessionCreationException_whenPlayGame_thenReturnInternalServerError() throws Exception {
        // given
        when(createSessionUseCase.createSession(any(CreateSessionRequestDTO.class))).thenThrow(new SessionCreationException("Error creating session"));

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // when & then
        mockMvc.perform(get("/play/testGame")
                .header("User-Agent", "Mozilla/5.0")
                .header("Referer", "http://example.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(createSessionUseCase).createSession(any(CreateSessionRequestDTO.class));
    }
}
