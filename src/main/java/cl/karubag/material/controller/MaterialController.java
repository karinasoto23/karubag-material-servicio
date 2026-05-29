package cl.karubag.material.controller;

import cl.karubag.material.dto.ExistsResponse;
import cl.karubag.material.dto.MaterialCreateRequest;
import cl.karubag.material.dto.MaterialResponse;
import cl.karubag.material.service.MaterialService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST que expone los endpoints públicos del microservicio de Materiales.
 *
 * Convenciones aplicadas:
 * - Versionado de API en la URL base: /api/v1/materiales
 * - Códigos HTTP semánticos (201 al crear, 204 al eliminar, etc.)
 
 * - Las excepciones lanzadas por el service son atrapadas por GlobalExceptionHandler.
 */
@RestController
@RequestMapping("/api/v1/materiales")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    /**
     * Crea un nuevo material.
     * POST /api/v1/materiales
     *
     * @return 201 Created + el material creado
     *         409 Conflict si el nombre ya existe
     */
    @PostMapping
    public ResponseEntity<MaterialResponse> crear(@RequestBody MaterialCreateRequest request) {
        MaterialResponse creado = materialService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * Lista todos los materiales.
     * GET /api/v1/materiales
     *
     * @return 200 OK + lista (puede ser vacía)
     */
    @GetMapping
    public ResponseEntity<List<MaterialResponse>> listarTodos() {
        List<MaterialResponse> materiales = materialService.listarTodos();
        return ResponseEntity.ok(materiales);
    }

    /**
     * Obtiene un material por su id.
     * GET /api/v1/materiales/{id}
     *
     * @return 200 OK + material
     *         404 Not Found si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> obtenerPorId(@PathVariable Long id) {
        MaterialResponse material = materialService.obtenerPorId(id);
        return ResponseEntity.ok(material);
    }

    /**
     * Indica si existe un material con el id dado.
     * GET /api/v1/materiales/{id}/exists
     *
     * Endpoint pensado para que OTROS microservicios validen
     * la existencia de un material antes de crear datos relacionados
     * (por ejemplo, pesaje-servicio antes de registrar un Pesaje).
     *
     * @return 200 OK + { "exists": true/false }
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<ExistsResponse> existePorId(@PathVariable Long id) {
        boolean existe = materialService.existePorId(id);
        return ResponseEntity.ok(new ExistsResponse(existe));
    }

    /**
     * Actualiza los datos modificables de un material.
     * PUT /api/v1/materiales/{id}
     *
     * @return 200 OK + material actualizado
     *         404 Not Found si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> actualizar(
            @PathVariable Long id,
            @RequestBody MaterialCreateRequest request) {

        MaterialResponse actualizado = materialService.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Elimina un material por su id.
     * DELETE /api/v1/materiales/{id}
     *
     * @return 204 No Content (eliminación exitosa, sin body de respuesta)
     *         404 Not Found si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        materialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
