package cl.karubag.material.exception;

/**
 * Excepción que se lanza cuando se busca un recurso que no existe.
 * Se traduce a HTTP 404 Not Found en GlobalExceptionHandler.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}