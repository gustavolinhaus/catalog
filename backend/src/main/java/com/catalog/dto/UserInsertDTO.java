package com.catalog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO extends UserDTO {
    private String password;
}
