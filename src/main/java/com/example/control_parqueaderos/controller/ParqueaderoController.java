package com.example.control_parqueaderos.controller;

import com.example.control_parqueaderos.dto.ParqueaderoDTO;
import com.example.control_parqueaderos.service.ParqueaderoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parqueaderos")
public class ParqueaderoController {

   @Autowired
    private ParqueaderoService parqueaderoService;

    @PostMapping
    public ResponseEntity<ParqueaderoDTO> createParqueadero(@Valid @RequestBody ParqueaderoDTO parqueaderoDTO){
        ParqueaderoDTO newParqueadero = parqueaderoService.createParqueadero(parqueaderoDTO);
        return new ResponseEntity<>(newParqueadero, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<ParqueaderoDTO>> getAllParqueaderos(){
//        List<ParqueaderoDTO> parqueaderos = parqueaderoService.getAllParqueaderos();
//        return new ResponseEntity<>(parqueaderos, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Page<ParqueaderoDTO>> getAllParqueaderos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ParqueaderoDTO> parqueaderos = parqueaderoService.getAllParqueaderosPageable(pageable);

        return ResponseEntity.ok(parqueaderos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParqueaderoDTO> getParqueaderoById(@PathVariable Long id){
        ParqueaderoDTO parqueadero = parqueaderoService.getParqueaderoById(id);
        return ResponseEntity.ok(parqueadero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParqueaderoDTO> updateParqueadero(@PathVariable Long id, @Valid @RequestBody ParqueaderoDTO parqueaderoDTO){
        ParqueaderoDTO updatedParqueadero = parqueaderoService.updateParqueadero(id, parqueaderoDTO);
        return ResponseEntity.ok(updatedParqueadero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParqueadero(@PathVariable Long id){
        parqueaderoService.deleteParqueadero(id);
        return ResponseEntity.ok("Parqueadero desactivado exitosamente");
    }

    @PutMapping("/{id}/reactivar")
    public ResponseEntity<ParqueaderoDTO> reactivarParqueadero(@PathVariable Long id){
        ParqueaderoDTO reactivated = parqueaderoService.reactivateParqueadero(id);
        return ResponseEntity.ok(reactivated);
    }
}
