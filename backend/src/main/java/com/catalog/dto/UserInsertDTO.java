package com.catalog.dto;

import com.catalog.services.validation.UserInsertValid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UserInsertValid
public class UserInsertDTO extends UserDTO {
    private String password;
}
