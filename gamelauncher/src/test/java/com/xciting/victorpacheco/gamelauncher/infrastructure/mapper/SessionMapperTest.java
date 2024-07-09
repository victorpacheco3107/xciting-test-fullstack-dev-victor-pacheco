package com.xciting.victorpacheco.gamelauncher.infrastructure.mapper;

import com.xciting.victorpacheco.gamelauncher.domain.entities.Session;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionRequestDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionResponseDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.entities.SessionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionMapperTest {

    private SessionMapper sessionMapper;

    @BeforeEach
    void setUp() {
        sessionMapper = Mappers.getMapper(SessionMapper.class);
    }

    @Test
    void givenCreateSessionRequestDTO_whenToDomain_thenReturnSession() {
        // given
        CreateSessionRequestDTO requestDTO = CreateSessionRequestDTO.builder()
                .gameCode("testGame")
                .language("en")
                .clientIp("127.0.0.1")
                .userAgent("Mozilla/5.0")
                .referer("http://example.com")
                .requestUrl("http://localhost/play/testGame")
                .build();

        // when
        Session session = sessionMapper.toDomain(requestDTO);

        // then
        assertEquals(requestDTO.getGameCode(), session.getGameCode());
        assertEquals(requestDTO.getLanguage(), session.getLanguage());
        assertEquals(requestDTO.getClientIp(), session.getClientIp());
        assertEquals(requestDTO.getUserAgent(), session.getUserAgent());
        assertEquals(requestDTO.getReferer(), session.getReferer());
        assertEquals(requestDTO.getRequestUrl(), session.getRequestUrl());
    }

    @Test
    void givenSession_whenToResponseDTO_thenReturnCreateSessionResponseDTO() {
        // given
        UUID sessionId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        Session session = Session.builder()
                .sessionId(sessionId)
                .createdAt(createdAt)
                .clientIp("127.0.0.1")
                .requestUrl("http://localhost/play/testGame")
                .userAgent("Mozilla/5.0")
                .gameCode("testGame")
                .language("en")
                .referer("http://example.com")
                .redirectUrl("http://redirect.url")
                .build();

        // when
        CreateSessionResponseDTO responseDTO = sessionMapper.toResponseDTO(session);

        // then
        assertEquals(session.getSessionId(), responseDTO.getSessionId());
        assertEquals(session.getCreatedAt(), responseDTO.getCreatedAt());
        assertEquals(session.getRedirectUrl(), responseDTO.getRedirectUrl());
    }

    @Test
    void givenSession_whenToEntity_thenReturnSessionEntity() {
        // given
        UUID sessionId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        Session session = Session.builder()
                .sessionId(sessionId)
                .createdAt(createdAt)
                .clientIp("127.0.0.1")
                .requestUrl("http://localhost/play/testGame")
                .userAgent("Mozilla/5.0")
                .gameCode("testGame")
                .language("en")
                .referer("http://example.com")
                .redirectUrl("http://redirect.url")
                .build();

        // when
        SessionEntity sessionEntity = sessionMapper.toEntity(session);

        // then
        assertEquals(session.getSessionId(), sessionEntity.getSessionId());
        assertEquals(session.getCreatedAt(), sessionEntity.getCreatedAt());
        assertEquals(session.getClientIp(), sessionEntity.getClientIp());
        assertEquals(session.getRequestUrl(), sessionEntity.getRequestUrl());
        assertEquals(session.getUserAgent(), sessionEntity.getUserAgent());
        assertEquals(session.getGameCode(), sessionEntity.getGameCode());
        assertEquals(session.getLanguage(), sessionEntity.getLanguage());
        assertEquals(session.getReferer(), sessionEntity.getReferer());
        assertEquals(session.getRedirectUrl(), sessionEntity.getRedirectUrl());
    }
}
