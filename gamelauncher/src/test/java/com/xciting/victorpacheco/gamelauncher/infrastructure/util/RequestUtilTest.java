package com.xciting.victorpacheco.gamelauncher.infrastructure.util;

import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestUtilTest {

    @Test
    void getClientIp_shouldReturnClientIpFromXForwardedForHeader() {
        // Given
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getHeader("X-Forwarded-For")).thenReturn("203.0.113.195");

        // When
        String clientIp = RequestUtil.getClientIp(webRequest);

        // Then
        assertEquals("203.0.113.195", clientIp);
    }

    @Test
    void getClientIp_shouldReturnClientIpFromRemoteAddressIfXForwardedForHeaderIsNull() {
        // Given
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getHeader("X-Forwarded-For")).thenReturn(null);
        when(webRequest.getRemoteUser()).thenReturn("203.0.113.195");

        // When
        String clientIp = RequestUtil.getClientIp(webRequest);

        // Then
        assertEquals("203.0.113.195", clientIp);
    }
}
