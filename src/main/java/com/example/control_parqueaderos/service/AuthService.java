package com.example.control_parqueaderos.service;

import com.example.control_parqueaderos.dto.AuthResponseDTO;
import com.example.control_parqueaderos.dto.LoginRequestDTO;
import com.example.control_parqueaderos.dto.RegisterRequestDTO;
import com.example.control_parqueaderos.dto.UsuarioResponseDTO;

public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO loginRequest);
    UsuarioResponseDTO register(RegisterRequestDTO registerRequest);
    boolean existsByEmail(String email);
}
