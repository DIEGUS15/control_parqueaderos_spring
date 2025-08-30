package com.example.control_parqueaderos.repository;

import com.example.control_parqueaderos.entity.Parqueadero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Long> {
    Optional<Parqueadero> findByNombre(String nombre);
    List<Parqueadero> findByNombreContainingIgnoreCase(String nombre);

    List<Parqueadero> findByActivoTrue();
    Page<Parqueadero> findByActivoTrue(Pageable pageable);
    Optional<Parqueadero> findByIdAndActivoTrue(Long id);
    List<Parqueadero> findBySocioIdAndActivoTrue(Long socioId);
}
