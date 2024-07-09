package com.xciting.victorpacheco.gamelauncher.infrastructure.adapters.outbound.persistence;

import com.xciting.victorpacheco.gamelauncher.application.ports.outbound.SessionRepository;
import com.xciting.victorpacheco.gamelauncher.infrastructure.entities.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JPA repository adapter for persisting session data.
 * Implements data persistence using Spring Data JPA.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@Component
public class JpaSessionRepository implements SessionRepository {

    private final SpringDataJpaSessionRepository repository;

    @Autowired
    public JpaSessionRepository(SpringDataJpaSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SessionEntity save(SessionEntity sessionEntity) {
        return repository.save(sessionEntity);
    }
}
