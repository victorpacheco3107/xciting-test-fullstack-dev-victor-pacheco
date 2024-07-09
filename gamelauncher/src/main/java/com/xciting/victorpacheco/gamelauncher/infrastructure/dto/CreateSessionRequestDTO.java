package com.xciting.victorpacheco.gamelauncher.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO for creating a session request
 */
@Data
@Builder
public class CreateSessionRequestDTO {
    private String gameCode;
    private String language;
    private String clientIp;
    private String userAgent;
    private String referer;
    private String requestUrl;
}
