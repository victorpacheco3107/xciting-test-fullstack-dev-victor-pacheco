package com.xciting.victorpacheco.gamelauncher.infrastructure.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity representing a game session.
 * Maps to the database table for storing session data.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "session")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String gameCode;
    private UUID sessionId;
    private String language;
    private LocalDateTime createdAt;
    private String clientIp;
    private String userAgent;
    private String referer;
    private String requestUrl;
    private String redirectUrl;
}
