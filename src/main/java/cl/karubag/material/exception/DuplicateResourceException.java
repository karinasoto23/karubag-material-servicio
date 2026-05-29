package cl.karubag.material.exception;

/**
 * Excepción que se lanza cuando se intenta crear un recurso que ya existe
 * (por ejemplo, un material con un nombre ya registrado).
 * Se traduce a HTTP 409 Conflict en GlobalExceptionHandler.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String mensaje) {
        super(mensaje);
    }
}
