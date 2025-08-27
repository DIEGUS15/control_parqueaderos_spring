package com.example.control_parqueaderos.dto;

import com.example.control_parqueaderos.entity.Usuario;

public class AuthResponseDTO {
    private String token;
    private String tipo = "Bearer";
    private Long id;
    private String email;
    private String nombre;
    private Usuario.Rol rol;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, Long id, String email, String nombre, Usuario.Rol rol) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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
}
