package com.xciting.victorpacheco.gamelauncher.domain.exceptions;

/**
 * Exception thrown when an error occurs during session creation.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
public class SessionCreationException extends Exception {
    public SessionCreationException(String message) {
        super(message);
    }
    public SessionCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
