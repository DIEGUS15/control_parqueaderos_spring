package com.example.control_parqueaderos.service.impl;

import com.example.control_parqueaderos.dto.AuthResponseDTO;
import com.example.control_parqueaderos.dto.LoginRequestDTO;
import com.example.control_parqueaderos.dto.RegisterRequestDTO;
import com.example.control_parqueaderos.dto.UsuarioResponseDTO;
import com.example.control_parqueaderos.entity.Usuario;
import com.example.control_parqueaderos.repository.UsuarioRepository;
import com.example.control_parqueaderos.service.AuthService;
import com.example.control_parqueaderos.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            Usuario usuario = (Usuario) authentication.getPrincipal();
            String token = jwtUtil.generateToken(usuario);

            return new AuthResponseDTO(
                    token,
                    usuario.getId(),
                    usuario.getEmail(),
                    usuario.getNombre(),
                    usuario.getRol()
            );

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales incorrectas");
        } catch (Exception e) {
            throw new RuntimeException("Error durante la autenticación", e);
        }
    }

    @Override
    public UsuarioResponseDTO register(RegisterRequestDTO registerRequest) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        // Crear nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(registerRequest.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        nuevoUsuario.setNombre(registerRequest.getNombre());
        nuevoUsuario.setRol(registerRequest.getRol());
        nuevoUsuario.setActivo(true);

        // Guardar usuario
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return new UsuarioResponseDTO(usuarioGuardado);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}