package com.xciting.victorpacheco.gamelauncher.domain.entities;

import com.xciting.victorpacheco.gamelauncher.domain.exceptions.SessionCreationException;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Session {
    private UUID sessionId;
    private LocalDateTime createdAt;
    private String clientIp;
    private String requestUrl;
    private String userAgent;
    private String gameCode;
    private String language;
    private String referer;
    private String redirectUrl;

    public void create(String baseRedirectUrl) throws SessionCreationException{
        if(baseRedirectUrl == null || baseRedirectUrl.isBlank()){
            throw new SessionCreationException("Error creating the session, invalid baseRedirectUrl");
        }
        this.sessionId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.redirectUrl = String.format(baseRedirectUrl, this.gameCode, this.sessionId, this.language);
    }
}
