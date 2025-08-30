package com.example.control_parqueaderos.service.impl;

import com.example.control_parqueaderos.dto.ParqueaderoDTO;
import com.example.control_parqueaderos.dto.UsuarioResponseDTO;
import com.example.control_parqueaderos.entity.Parqueadero;
import com.example.control_parqueaderos.entity.Usuario;
import com.example.control_parqueaderos.repository.ParqueaderoRepository;
import com.example.control_parqueaderos.repository.UsuarioRepository;
import com.example.control_parqueaderos.service.ParqueaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ParqueaderoDTO createParqueadero(ParqueaderoDTO parqueaderoDTO) {

        Usuario socio = usuarioRepository.findById(parqueaderoDTO.getSocioId()).orElseThrow(() -> new RuntimeException("Socio no encontrado con id: " + parqueaderoDTO.getSocioId()));

        if(socio.getRol() !=  Usuario.Rol.SOCIO) {
            throw new RuntimeException("El usuario debe tener el rol SOCIO");
        }

        if(!socio.getActivo()) {
            throw new RuntimeException("El socio debe estar activo");
        }

        //Convertir DTO a Entity
        Parqueadero parqueadero = new Parqueadero();
        parqueadero.setNombre(parqueaderoDTO.getNombre());
        parqueadero.setDireccion(parqueaderoDTO.getDireccion());
        parqueadero.setCapacidad(parqueaderoDTO.getCapacidad());
        parqueadero.setCostoHora(parqueaderoDTO.getCostoHora());
        parqueadero.setSocio(socio);

        Parqueadero savedParqueadero = parqueaderoRepository.save(parqueadero);

        return convertirEntityADTO(savedParqueadero);
    }

    @Override
    public List<ParqueaderoDTO> getAllParqueaderos() {
        List<Parqueadero> parqueaderos = parqueaderoRepository.findAll();
        return parqueaderos.stream().map(this::convertirEntityADTO).collect(Collectors.toList());
    }

    public Page<ParqueaderoDTO> getAllParqueaderosPageable(Pageable pageable){
        Page<Parqueadero> parqueaderos = parqueaderoRepository.findByActivoTrue(pageable);
        return parqueaderos.map(this::convertirEntityADTO);
    }

    public List<ParqueaderoDTO> getAllParqueaderosActivos() {
        List<Parqueadero> parqueaderos = parqueaderoRepository.findByActivoTrue();
        return parqueaderos.stream().map(this::convertirEntityADTO).collect(Collectors.toList());
    }

    @Override
    public ParqueaderoDTO getParqueaderoById(Long id) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id).orElseThrow(() -> new RuntimeException("Parqueadero no encontrado con id: "+ id));
        return convertirEntityADTO(parqueadero);
    }

    @Override
    public ParqueaderoDTO updateParqueadero(Long id, ParqueaderoDTO parqueaderoDTO) {
        Parqueadero parqueaderoExistente = parqueaderoRepository.findById(id).orElseThrow(() -> new RuntimeException("Parqueadero no encontrado con id: "+ id));

        if(parqueaderoDTO.getSocioId() != null && !parqueaderoExistente.getSocio().getId().equals(parqueaderoDTO.getSocioId())) {
            Usuario newSocio = usuarioRepository.findById(parqueaderoDTO.getSocioId()).orElseThrow(() -> new RuntimeException("Socio no encontrado con id: " + parqueaderoDTO.getSocioId()));

            if(newSocio.getRol() !=  Usuario.Rol.SOCIO) {
                throw new RuntimeException("El usuario debe tener rol SOCIO");
            }

            if(!newSocio.getActivo()) {
                throw new RuntimeException("El socio debe estar activo");
            }
            parqueaderoExistente.setSocio(newSocio);
        }

        parqueaderoExistente.setNombre(parqueaderoDTO.getNombre());
        parqueaderoExistente.setDireccion(parqueaderoDTO.getDireccion());
        parqueaderoExistente.setCapacidad(parqueaderoDTO.getCapacidad());
        parqueaderoExistente.setCostoHora(parqueaderoDTO.getCostoHora());

        if (parqueaderoDTO.getActivo() != null) {
            parqueaderoExistente.setActivo(parqueaderoDTO.getActivo());
        }

        Parqueadero UpdatedParqueadero = parqueaderoRepository.save(parqueaderoExistente);

        return convertirEntityADTO(UpdatedParqueadero);
    }

    @Override
    public void deleteParqueadero(Long id) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id).orElseThrow(() -> new RuntimeException("Parqueadero no encontrado con id: " + id));

        parqueadero.setActivo(false);
        parqueaderoRepository.save(parqueadero);
    }

    public ParqueaderoDTO reactivateParqueadero(Long id) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id).orElseThrow(() -> new RuntimeException("Parqueadero no encontrado con id: " + id));

        parqueadero.setActivo(true);
        Parqueadero reactivated = parqueaderoRepository.save(parqueadero);
        return convertirEntityADTO(reactivated);
    }

    private ParqueaderoDTO convertirEntityADTO(Parqueadero parqueadero) {
        ParqueaderoDTO dto = new ParqueaderoDTO();
        dto.setId(parqueadero.getId());
        dto.setNombre(parqueadero.getNombre());
        dto.setDireccion(parqueadero.getDireccion());
        dto.setCapacidad(parqueadero.getCapacidad());
        dto.setCostoHora(parqueadero.getCostoHora());
        dto.setActivo(parqueadero.getActivo());
        dto.setSocioId(parqueadero.getSocio().getId());
        dto.setSocio(new UsuarioResponseDTO(parqueadero.getSocio()));

        return dto;
    }

}
