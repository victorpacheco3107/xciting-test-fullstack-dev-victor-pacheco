package com.xciting.victorpacheco.gamelauncher.infrastructure.util;

import org.springframework.web.context.request.WebRequest;

/**
 * Utility class for handling HTTP request related operations.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
public class RequestUtil {

    /**
     * Retrieves the client IP address from the request.
     *
     * @param request The WebRequest object.
     * @return The client IP address.
     */
    public static String getClientIp(WebRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isBlank()) {
            return request.getRemoteUser();
        }
        return xfHeader.split(",")[0];
    }
}
