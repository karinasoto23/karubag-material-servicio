package cl.karubag.material.dto;

import java.math.BigDecimal;

/**
 * DTO de entrada: lo que el cliente envía para crear o actualizar un material.
 *
 * No incluye 'id' (se autogenera) ni 'activo', 'creadoEn', 'actualizadoEn'
 * (se gestionan automáticamente en el Service y la entidad).
 */
public class MaterialCreateRequest {

    private String nombre;
    private String descripcion;
    private String categoria;
    private BigDecimal precioKgClp;
    private String unidadMedida;
    private Boolean reciclable;

    public MaterialCreateRequest() {
    }

    // ─────────────────────────────────────────────
    // Getters y Setters
    // ─────────────────────────────────────────────

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
}
