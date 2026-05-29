package cl.karubag.material.mapper;

import cl.karubag.material.dto.MaterialCreateRequest;
import cl.karubag.material.dto.MaterialResponse;
import cl.karubag.material.model.Material;

import org.springframework.stereotype.Component;

/**
 * Mapper encargado de convertir entre las diferentes representaciones del Material:
 * - MaterialCreateRequest (DTO de entrada)  -> Material (entidad JPA)
 * - Material (entidad JPA)                  -> MaterialResponse (DTO de salida)
 *
 * Decisión de diseño: al convertir DTO a entidad, se asigna activo=true
 * automáticamente. El cliente NO decide si un material nace activo o no:
 * todo material recién registrado está disponible para recolección.
 */
@Component
public class MaterialMapper {

    /**
     * Convierte el DTO de entrada en una entidad Material lista para persistir.
     * Asigna 'activo = true' por defecto (regla de negocio).
     */
    public Material aEntidad(MaterialCreateRequest request) {
        Material material = new Material();
        material.setNombre(request.getNombre());
        material.setDescripcion(request.getDescripcion());
        material.setCategoria(request.getCategoria());
        material.setPrecioKgClp(request.getPrecioKgClp());
        material.setUnidadMedida(request.getUnidadMedida());
        material.setReciclable(request.getReciclable());
        material.setActivo(true);
        return material;
    }

    /**
     * Convierte una entidad Material en el DTO de salida que ve el cliente.
     * Incluye todos los campos (no hay datos sensibles que ocultar).
     */
    public MaterialResponse aResponse(Material material) {
        MaterialResponse response = new MaterialResponse();
        response.setId(material.getId());
        response.setNombre(material.getNombre());
        response.setDescripcion(material.getDescripcion());
        response.setCategoria(material.getCategoria());
        response.setPrecioKgClp(material.getPrecioKgClp());
        response.setUnidadMedida(material.getUnidadMedida());
        response.setReciclable(material.getReciclable());
        response.setActivo(material.getActivo());
        response.setCreadoEn(material.getCreadoEn());
        response.setActualizadoEn(material.getActualizadoEn());
        return response;
    }
}
