package com.xciting.victorpacheco.gamelauncher.application.services;

import com.xciting.victorpacheco.gamelauncher.domain.exceptions.SessionCreationException;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class SessionServiceTest {


    @InjectMocks
    private SessionService sessionService;

    @Value("${game.redirect.url}")
    private String redirectUrl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(sessionService, "redirectUrl", redirectUrl);
    }

    @Test
    void givenSessionCreationException_whenCreateSession_thenThrowSessionCreationException() throws SessionCreationException {
        // given
        CreateSessionRequestDTO requestDTO = CreateSessionRequestDTO.builder()
                .gameCode("testGame")
                .language("en")
                .clientIp("127.0.0.1")
                .userAgent("Mozilla/5.0")
                .referer("http://example.com")
                .requestUrl("http://localhost/play/testGame")
                .build();

        // when & then
        SessionCreationException exception = assertThrows(SessionCreationException.class, () -> sessionService.createSession(requestDTO));
        assertEquals("Error creating session", exception.getMessage());

    }

    @Test
    void givenGenericException_whenCreateSession_thenThrowSessionCreationException() {
        // given
        CreateSessionRequestDTO requestDTO = CreateSessionRequestDTO.builder()
                .gameCode("testGame")
                .language("en")
                .clientIp("127.0.0.1")
                .userAgent("Mozilla/5.0")
                .referer("http://example.com")
                .requestUrl("http://localhost/play/testGame")
                .build();

        // when & then
        SessionCreationException exception = assertThrows(SessionCreationException.class, () -> sessionService.createSession(requestDTO));
        assertEquals("Error creating session", exception.getMessage());

    }
}
