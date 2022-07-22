package com.catalog.services.mapper;

import java.util.List;

public interface EntityMapper <Entity, DTO> {
    Entity toEntity(DTO dto);
    DTO toDTO(Entity entity);
    List<Entity> toEntities(List<DTO> dtos);
    List<DTO> toDTOs(List<Entity> entities);
}
