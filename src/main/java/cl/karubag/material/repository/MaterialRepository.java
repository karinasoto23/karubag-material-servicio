package cl.karubag.material.repository;

import cl.karubag.material.model.Material;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para la entidad Material.
 *
 * Hereda automáticamente de JpaRepository todos los métodos CRUD básicos:
 * save(), findById(), findAll(), deleteById(), existsById(), count(), etc.
 *
 * Métodos adicionales personalizados se declaran aquí y Spring Data JPA
 * genera la implementación automáticamente a partir del nombre del método.
 */
public interface MaterialRepository extends JpaRepository<Material, Long> {

    /**
     * Verifica si existe un material con el nombre dado.
     * Lo usamos en el Service para validar duplicados antes de crear.
     *
     * Spring genera automáticamente la query equivalente a:
     *   SELECT COUNT(*) > 0 FROM material WHERE nombre = ?
     */
    boolean existsByNombre(String nombre);
}

 
