package com.xciting.victorpacheco.gamelauncher.infrastructure.adapters.outbound.persistence;

import com.xciting.victorpacheco.gamelauncher.infrastructure.entities.SessionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JpaSessionRepositoryTest {

    @Mock
    private SpringDataJpaSessionRepository springDataJpaSessionRepository;

    @InjectMocks
    private JpaSessionRepository jpaSessionRepository;

    private SessionEntity sessionEntity;

    @BeforeEach
    void setUp() {
        sessionEntity = new SessionEntity();
        sessionEntity.setGameCode("game123");
        sessionEntity.setLanguage("en");
        sessionEntity.setClientIp("192.168.0.1");
        sessionEntity.setUserAgent("Mozilla/5.0");
        sessionEntity.setReferer("http://example.com");
        sessionEntity.setRequestUrl("http://localhost/play/game123");
    }

    @Test
    void save_shouldCallRepositorySave() {
        // Given
        when(springDataJpaSessionRepository.save(any(SessionEntity.class))).thenReturn(sessionEntity);

        // When
        SessionEntity savedEntity = jpaSessionRepository.save(sessionEntity);

        // Then
        verify(springDataJpaSessionRepository, times(1)).save(any(SessionEntity.class));
        assertEquals(sessionEntity, savedEntity);
    }
}
