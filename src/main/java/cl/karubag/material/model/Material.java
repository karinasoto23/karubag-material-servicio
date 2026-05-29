package cl.karubag.material.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad Material: representa un tipo de residuo reciclable que la empresa
 * Karubag puede recolectar y procesar (ej: Papel, Cartón, Vidrio, Plástico).
 *
 * Decisiones de diseño:
 * - 'nombre' es único: actúa como identificador semántico
 *   (no se pueden tener dos materiales llamados "Papel").
 * - 'precioKgClp' usa BigDecimal para precisión exacta en cálculos monetarios.
 * - 'unidadMedida' por defecto será "kg" pero permite flexibilidad
 *   para materiales líquidos ("litros") o conteo ("unidades").
 * - 'categoria' agrupa materiales para reportes (ej: "PAPELES", "VIDRIOS").
 * - 'reciclable' indica si el material puede ser procesado.
 * - 'activo' permite ocultar materiales sin borrarlos (útil si en el
 *   futuro queremos preservar historial).
 */
@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(name = "precio_kg_clp", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioKgClp;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @Column(nullable = false)
    private Boolean reciclable;

    @Column(nullable = false)
    private Boolean activo;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    // ─────────────────────────────────────────────
    // Ciclo de vida JPA: timestamps automáticos
    // ─────────────────────────────────────────────

    @PrePersist
    protected void onCreate() {
        LocalDateTime ahora = LocalDateTime.now();
        this.creadoEn = ahora;
        this.actualizadoEn = ahora;
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = LocalDateTime.now();
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

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
