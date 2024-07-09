package com.xciting.victorpacheco.gamelauncher.infrastructure.adapters.outbound.persistence;

import com.xciting.victorpacheco.gamelauncher.infrastructure.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for session entities.
 * Provides methods for interacting with the database.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@Repository
public interface SpringDataJpaSessionRepository extends JpaRepository<SessionEntity, Long> {
}
