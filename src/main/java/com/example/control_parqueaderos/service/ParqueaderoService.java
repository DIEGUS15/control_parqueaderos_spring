package com.example.control_parqueaderos.service;

import com.example.control_parqueaderos.dto.ParqueaderoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParqueaderoService {
    ParqueaderoDTO createParqueadero(ParqueaderoDTO parqueaderoDTO);
    List<ParqueaderoDTO> getAllParqueaderos();
    Page<ParqueaderoDTO> getAllParqueaderosPageable(Pageable pageable);
    List<ParqueaderoDTO> getAllParqueaderosActivos();
    ParqueaderoDTO getParqueaderoById(Long id);
    ParqueaderoDTO updateParqueadero(Long id, ParqueaderoDTO parqueaderoDTO);
    void deleteParqueadero(Long id);
    ParqueaderoDTO reactivateParqueadero(Long id);

}
