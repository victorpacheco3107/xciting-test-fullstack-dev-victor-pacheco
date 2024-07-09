package com.xciting.victorpacheco.gamelauncher.infrastructure.mapper;

import com.xciting.victorpacheco.gamelauncher.domain.entities.Session;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionRequestDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.dto.CreateSessionResponseDTO;
import com.xciting.victorpacheco.gamelauncher.infrastructure.entities.SessionEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between SessionDTO, Session, and SessionEntity.
 * Uses MapStruct for automatic mapping.
 *
 * @autor <a href="mailto:victorpacheco3107@gmail.com">Victor Pacheco</a>
 */
@Mapper(componentModel = "spring")
public interface SessionMapper {
    Session toDomain(CreateSessionRequestDTO createSessionRequestDTO);
    CreateSessionResponseDTO toResponseDTO(Session session);
    SessionEntity toEntity(Session session);
}
