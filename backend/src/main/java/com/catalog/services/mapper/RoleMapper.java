package com.catalog.services.mapper;

import com.catalog.dto.RoleDTO;
import com.catalog.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<Role, RoleDTO> {
}
