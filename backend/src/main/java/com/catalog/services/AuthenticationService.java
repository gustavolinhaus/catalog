package com.catalog.services;

import com.catalog.dto.AuthenticationRequestDTO;
import com.catalog.dto.AuthenticationResponseDTO;
import com.catalog.dto.UserInsertDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO register(UserInsertDTO userInsertDTO);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
}
