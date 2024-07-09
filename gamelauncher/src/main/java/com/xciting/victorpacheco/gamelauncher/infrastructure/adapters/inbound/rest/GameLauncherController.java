package com.xciting.victorpacheco.gamelauncher.infrastructure.adapters.inbound.rest;

import com.xciting.victorpacheco.gamelauncher.application.ports.inbound.CreateSessionUseCase;
import com.xciting.victorpacheco.gamelauncher.domain.exceptions.SessionCreationException;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionRequestDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionResponseDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.util.RequestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for handling game launch requests.
 * Implements adapters for web interfaces, handling incoming HTTP requests.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@RestController
public class GameLauncherController {

    private final CreateSessionUseCase createSessionUseCase;

    public GameLauncherController(CreateSessionUseCase createSessionUseCase) {
        this.createSessionUseCase = createSessionUseCase;
    }

    @GetMapping("/play/{gameCode}")
    public ResponseEntity<Void> playGame(@PathVariable String gameCode,
                                         @RequestParam(defaultValue = "en") String language,
                                         @RequestHeader(value = "User-Agent") String userAgent,
                                         @RequestHeader(value = "Referer", required = false) String referer,
                                         WebRequest request) {
        try {
            CreateSessionRequestDTO sessionRequestDTO = CreateSessionRequestDTO.builder()
                    .gameCode(gameCode)
                    .language(language)
                    .clientIp(RequestUtil.getClientIp(request))
                    .userAgent(userAgent)
                    .referer(referer)
                    .requestUrl(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                    .build();

            CreateSessionResponseDTO sessionResponseDTO = createSessionUseCase.createSession(sessionRequestDTO);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(ServletUriComponentsBuilder.fromHttpUrl(sessionResponseDTO.getRedirectUrl()).build().toUri())
                    .build();
        } catch (SessionCreationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
