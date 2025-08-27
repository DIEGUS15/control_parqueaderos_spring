package com.example.control_parqueaderos.controller;

import com.example.control_parqueaderos.dto.AuthResponseDTO;
import com.example.control_parqueaderos.dto.LoginRequestDTO;
import com.example.control_parqueaderos.dto.RegisterRequestDTO;
import com.example.control_parqueaderos.dto.UsuarioResponseDTO;
import com.example.control_parqueaderos.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        AuthResponseDTO authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        UsuarioResponseDTO usuarioResponse = authService.register(registerRequest);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @GetMapping("/check-email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = authService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}