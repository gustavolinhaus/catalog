package com.catalog.services.impl;

import com.catalog.config.JwtService;
import com.catalog.dto.AuthenticationRequestDTO;
import com.catalog.dto.AuthenticationResponseDTO;
import com.catalog.dto.UserInsertDTO;
import com.catalog.entities.User;
import com.catalog.repositories.UserRepository;
import com.catalog.services.AuthenticationService;
import com.catalog.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthenticationResponseDTO register(UserInsertDTO userInsertDTO) {
        User user = userMapper.toEntity(userInsertDTO);
        user.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));
        user = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
