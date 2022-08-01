package com.catalog.services;

import com.catalog.dto.UserDTO;
import com.catalog.dto.UserInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> findAllPaged(Pageable pageable);
    UserDTO findById(Long id);
    UserDTO insert(UserInsertDTO dto);
    UserDTO update(Long id, UserDTO userDTO);
    void delete(Long id);
}
