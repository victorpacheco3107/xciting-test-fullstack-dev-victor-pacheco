package com.xciting.victorpacheco.gamelauncher.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data transfer object for session data.
 * Used for transferring session data between layers.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@Data
@Builder
public class SessionDTO {
    private String gameCode;
    private String language;
    private String clientIp;
    private String userAgent;
    private String referer;
    private String requestUrl;
    private String redirectUrl;
}
