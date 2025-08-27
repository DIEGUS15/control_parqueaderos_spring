package com.example.control_parqueaderos.repository;

import com.example.control_parqueaderos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmailAndActivoTrue(String email);
}
