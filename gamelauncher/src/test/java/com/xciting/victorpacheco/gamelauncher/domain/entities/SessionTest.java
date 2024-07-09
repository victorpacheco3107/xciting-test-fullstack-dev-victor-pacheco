package com.xciting.victorpacheco.gamelauncher.domain.entities;

import com.xciting.victorpacheco.gamelauncher.domain.exceptions.SessionCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class SessionTest {

    private Session.SessionBuilder sessionBuilder;

    @BeforeEach
    void setUp() {
        sessionBuilder = Session.builder()
                .clientIp("127.0.0.1")
                .requestUrl("http://localhost/play/testGame")
                .userAgent("Mozilla/5.0")
                .gameCode("testGame")
                .language("en")
                .referer("http://example.com");
    }

    @Test
    void givenValidBaseRedirectUrl_whenCreate_thenSetSessionIdAndCreatedAtAndRedirectUrl() throws SessionCreationException {
        // given
        Session session = sessionBuilder.build();
        UUID mockUUID = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        String baseRedirectUrl = "https://games.xciting.com/%s?session=%s&language=%s";

        try (MockedStatic<UUID> mockedUUID = mockStatic(UUID.class);
             MockedStatic<LocalDateTime> mockedLocalDateTime = mockStatic(LocalDateTime.class)) {

            mockedUUID.when(UUID::randomUUID).thenReturn(mockUUID);
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(now);

            // when
            session.create(baseRedirectUrl);

            // then
            assertEquals(mockUUID, session.getSessionId());
            assertEquals(now, session.getCreatedAt());
            assertEquals("https://games.xciting.com/testGame?session=" + mockUUID + "&language=en", session.getRedirectUrl());
        }
    }

    @Test
    void givenNullBaseRedirectUrl_whenCreate_thenThrowSessionCreationException() {
        // given
        Session session = sessionBuilder.build();
        String baseRedirectUrl = null;

        // when & then
        SessionCreationException exception = assertThrows(SessionCreationException.class, () -> session.create(baseRedirectUrl));
        assertEquals("Error creating the session, invalid baseRedirectUrl", exception.getMessage());
        assertNull(session.getSessionId());
        assertNull(session.getCreatedAt());
        assertNull(session.getRedirectUrl());
    }

    @Test
    void givenEmptyBaseRedirectUrl_whenCreate_thenThrowSessionCreationException() {
        // given
        Session session = sessionBuilder.build();
        String baseRedirectUrl = "";

        // when & then
        SessionCreationException exception = assertThrows(SessionCreationException.class, () -> session.create(baseRedirectUrl));
        assertEquals("Error creating the session, invalid baseRedirectUrl", exception.getMessage());
        assertNull(session.getSessionId());
        assertNull(session.getCreatedAt());
        assertNull(session.getRedirectUrl());
    }

    @Test
    void givenBlankBaseRedirectUrl_whenCreate_thenThrowSessionCreationException() {
        // given
        Session session = sessionBuilder.build();
        String baseRedirectUrl = "   ";

        // when & then
        SessionCreationException exception = assertThrows(SessionCreationException.class, () -> session.create(baseRedirectUrl));
        assertEquals("Error creating the session, invalid baseRedirectUrl", exception.getMessage());
        assertNull(session.getSessionId());
        assertNull(session.getCreatedAt());
        assertNull(session.getRedirectUrl());
    }
}
