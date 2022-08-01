package com.catalog.services.mapper;

import com.catalog.dto.UserDTO;
import com.catalog.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserDTO> {
}
