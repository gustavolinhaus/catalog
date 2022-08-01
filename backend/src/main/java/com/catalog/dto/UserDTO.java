package com.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    Set<RoleDTO> roles = new HashSet<>();
}
