package com.example.control_parqueaderos.dto;

import com.example.control_parqueaderos.entity.Usuario;
import java.time.LocalDateTime;

public class UsuarioResponseDTO {

    private Long id;
    private String email;
    private String nombre;
    private Usuario.Rol rol;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Constructores
    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nombre = usuario.getNombre();
        this.rol = usuario.getRol();
        this.activo = usuario.getActivo();
        this.fechaCreacion = usuario.getFechaCreacion();
        this.fechaActualizacion = usuario.getFechaActualizacion();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario.Rol getRol() {
        return rol;
    }

    public void setRol(Usuario.Rol rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}