package com.example.control_parqueaderos.config;

import com.example.control_parqueaderos.entity.Usuario;
import com.example.control_parqueaderos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuario admin por defecto si no existe
        if (!usuarioRepository.existsByEmail("admin@parqueaderos.com")) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@parqueaderos.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setNombre("Administrador Principal");
            admin.setRol(Usuario.Rol.ADMIN);
            admin.setActivo(true);

            usuarioRepository.save(admin);
            System.out.println("Usuario administrador creado:");
            System.out.println("Email: admin@parqueaderos.com");
            System.out.println("Password: admin123");
        }
    }
}