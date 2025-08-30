package com.example.control_parqueaderos.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ParqueaderoDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String direccion;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    private Integer capacidad;

    @NotNull(message = "El costo por hora es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo por hora debe ser mayor a cero")
    private BigDecimal costoHora;

    @NotNull(message = "El socio es obligatorio")
    private Long socioId;

    private UsuarioResponseDTO socio;

    private Boolean activo;

    public ParqueaderoDTO() {
    }

    public ParqueaderoDTO(Long id, String nombre, String direccion, Integer capacidad, BigDecimal costoHora, Boolean activo, UsuarioResponseDTO socio) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.costoHora = costoHora;
        this.activo = activo;
        this.socio = socio;
        this.socioId = socio != null ? socio.getId() : null;
    }

    public Long getSocioId() {
        return socioId;
    }

    public void setSocioId(Long socioId) {
        this.socioId = socioId;
    }

    public UsuarioResponseDTO getSocio() {
        return socio;
    }

    public void setSocio(UsuarioResponseDTO socio) {
        this.socio = socio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public BigDecimal getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(BigDecimal costoHora) {
        this.costoHora = costoHora;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
