package com.catalog.services.mapper;

import java.util.List;

public interface EntityMapper <Entity, Dto> {
    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
    List<Entity> toEntities(List<Dto> dtos);
    List<Dto> toDtos(List<Entity> entities);
}
