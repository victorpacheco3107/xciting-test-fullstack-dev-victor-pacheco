package com.xciting.victorpacheco.gamelauncher.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for session response
 */
@Data
@Builder
public class CreateSessionResponseDTO {
    private UUID sessionId;
    private LocalDateTime createdAt;
    private String redirectUrl;
}
