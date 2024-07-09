package com.xciting.victorpacheco.gamelauncher.application.ports.outbound;

import com.xciting.victorpacheco.gamelauncher.infrastructure.entities.SessionEntity;

/**
 * Interface for the session repository.
 * Defines methods for saving session data.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
public interface SessionRepository {

    /**
     * Saves a session entity.
     *
     * @param sessionEntity The session entity to be saved.
     * @return The saved session entity.
     */
    SessionEntity save(SessionEntity sessionEntity);
}
