package com.catalog.services.impl;

import com.catalog.dto.UserDTO;
import com.catalog.dto.UserInsertDTO;
import com.catalog.dto.UserUpdateDTO;
import com.catalog.entities.User;
import com.catalog.repositories.UserRepository;
import com.catalog.services.UserService;
import com.catalog.services.exceptions.DatabaseException;
import com.catalog.services.exceptions.ResourceNotFoundException;
import com.catalog.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO userInsertDTO) {
        User user = userMapper.toEntity(userInsertDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO userUpdateDTO) {
        userUpdateDTO.setId(id);
        User user = userMapper.toEntity(userUpdateDTO);
        user.setPassword(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found")).getPassword());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
            userRepository.flush();
        }
        catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("Entity not found");
        }
        catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
