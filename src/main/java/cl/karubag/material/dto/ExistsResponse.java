package cl.karubag.material.dto;

/**
 * DTO simple para responder si una entidad existe o no.
 *
 * Lo usan otros microservicios (vía WebClient) para validar
 * que un material existe antes de crear datos relacionados.
 *
 * Ejemplo: pesaje-servicio antes de crear un Pesaje
 * verifica que el material con ese id exista.
 *
 * Respuesta JSON: { "exists": true } o { "exists": false }
 */
public class ExistsResponse {

    private boolean exists;

    public ExistsResponse() {
    }

    public ExistsResponse(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
