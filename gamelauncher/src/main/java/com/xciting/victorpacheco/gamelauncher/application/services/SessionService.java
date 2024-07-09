package com.xciting.victorpacheco.gamelauncher.application.services;

import com.xciting.victorpacheco.gamelauncher.application.ports.inbound.CreateSessionUseCase;
import com.xciting.victorpacheco.gamelauncher.application.ports.outbound.SessionRepository;
import com.xciting.victorpacheco.gamelauncher.domain.entities.Session;
import com.xciting.victorpacheco.gamelauncher.domain.exceptions.SessionCreationException;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionRequestDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionResponseDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.entities.SessionEntity;
import com.xciting.victorpacheco.gamelauncher.infrastructure.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service implementation for creating game sessions.
 * Coordinates activities between the ports and the domain.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@Service
public class SessionService implements CreateSessionUseCase {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Value("${game.redirect.url}")
    private String redirectUrl;

    public SessionService(SessionRepository sessionRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public CreateSessionResponseDTO createSession(CreateSessionRequestDTO createSessionRequestDTO) throws SessionCreationException{
        try {
            Session session = sessionMapper.toDomain(createSessionRequestDTO);
            session.create(redirectUrl);
            SessionEntity sessionEntity = sessionMapper.toEntity(session);
            sessionRepository.save(sessionEntity);
            return sessionMapper.toResponseDTO(session);
        } catch (SessionCreationException e){
            throw e;
        } catch (Exception e) {
            throw new SessionCreationException("Error creating session", e);
        }
    }
}
