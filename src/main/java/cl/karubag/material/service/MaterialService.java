package cl.karubag.material.service;

import cl.karubag.material.dto.MaterialCreateRequest;
import cl.karubag.material.dto.MaterialResponse;
import cl.karubag.material.exception.DuplicateResourceException;
import cl.karubag.material.exception.ResourceNotFoundException;
import cl.karubag.material.mapper.MaterialMapper;
import cl.karubag.material.model.Material;
import cl.karubag.material.repository.MaterialRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para Material.
 *
 * Responsabilidades:
 * - Coordinar Repository y Mapper.
 * - Validar reglas de negocio (ej: nombre único).
 * - Lanzar excepciones de dominio cuando algo no cumple las reglas.
 * - Gestionar transacciones con @Transactional.
 *
 * Inyección por constructor: no se usa @Autowired en campos
 * (decisión moderna que favorece la inmutabilidad y facilita los tests).
 */
@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    /**
     * Crea un nuevo material.
     * Valida que el nombre no esté registrado antes de persistir.
     *
     * @throws DuplicateResourceException si el nombre ya existe (HTTP 409)
     */
    @Transactional
    public MaterialResponse crear(MaterialCreateRequest request) {
        if (materialRepository.existsByNombre(request.getNombre())) {
            throw new DuplicateResourceException(
                    "Ya existe un material con el nombre: " + request.getNombre()
            );
        }

        Material nuevoMaterial = materialMapper.aEntidad(request);
        Material guardado = materialRepository.save(nuevoMaterial);
        return materialMapper.aResponse(guardado);
    }

    /**
     * Obtiene un material por su id.
     *
     * @throws ResourceNotFoundException si no existe (HTTP 404)
     */
    @Transactional(readOnly = true)
    public MaterialResponse obtenerPorId(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Material con id " + id + " no existe"
                ));
        return materialMapper.aResponse(material);
    }

    /**
     * Lista todos los materiales registrados.
     */
    @Transactional(readOnly = true)
    public List<MaterialResponse> listarTodos() {
        return materialRepository.findAll()
                .stream()
                .map(materialMapper::aResponse)
                .toList();
    }

    /**
     * Indica si existe un material con el id dado.
     * Lo usan otros microservicios (vía WebClient) para validaciones cruzadas.
     * Por ejemplo, pesaje-servicio antes de registrar el peso de un material.
     */
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return materialRepository.existsById(id);
    }

    /**
     * Actualiza los datos modificables de un material existente.
     * No se actualiza el nombre (es identificador semántico del material).
     * Sí se puede modificar 'reciclable' aquí si la empresa cambia su política.
     *
     * @throws ResourceNotFoundException si el material no existe (HTTP 404)
     */
    @Transactional
    public MaterialResponse actualizar(Long id, MaterialCreateRequest request) {
        Material existente = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Material con id " + id + " no existe"
                ));

        existente.setDescripcion(request.getDescripcion());
        existente.setCategoria(request.getCategoria());
        existente.setPrecioKgClp(request.getPrecioKgClp());
        existente.setUnidadMedida(request.getUnidadMedida());
        existente.setReciclable(request.getReciclable());

        Material actualizado = materialRepository.save(existente);
        return materialMapper.aResponse(actualizado);
    }

    /**
     * Elimina un material por su id (hard delete).
     *
     * @throws ResourceNotFoundException si el material no existe (HTTP 404)
     */
    @Transactional
    public void eliminar(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Material con id " + id + " no existe"
            );
        }
        materialRepository.deleteById(id);
    }
}
