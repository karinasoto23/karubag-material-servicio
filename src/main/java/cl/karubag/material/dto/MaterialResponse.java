package cl.karubag.material.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO de salida: lo que la API devuelve al cliente.
 *
 * Incluye todos los campos del Material (no hay datos sensibles que ocultar
 * como en el caso del passwordHash de Usuario).
 */
public class MaterialResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private BigDecimal precioKgClp;
    private String unidadMedida;
    private Boolean reciclable;
    private Boolean activo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public MaterialResponse() {
    }

    // ─────────────────────────────────────────────
    // Getters y Setters
    // ─────────────────────────────────────────────

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecioKgClp() {
        return precioKgClp;
    }

    public void setPrecioKgClp(BigDecimal precioKgClp) {
        this.precioKgClp = precioKgClp;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Boolean getReciclable() {
        return reciclable;
    }

    public void setReciclable(Boolean reciclable) {
        this.reciclable = reciclable;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
