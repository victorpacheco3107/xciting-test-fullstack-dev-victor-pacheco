package com.xciting.victorpacheco.gamelauncher.application.ports.inbound;

import com.xciting.victorpacheco.gamelauncher.domain.exceptions.SessionCreationException;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionRequestDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionResponseDTO;


/**
 * Interface defining the contract for creating game sessions.
 * Used by external agents to interact with the application.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
public interface CreateSessionUseCase {

    /**
     * Creates a new game session.
     *
     * @param createSessionRequestDTO Data transfer object containing session details.
     * @return The created session dto.
     * @throws SessionCreationException If an error occurs during session creation.
     */
    CreateSessionResponseDTO createSession(CreateSessionRequestDTO createSessionRequestDTO) throws SessionCreationException;
}
