package cl.duoc.bodegas_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DireccionException.class)
    public ResponseEntity<?> errorEnDireccion(DireccionException ex){
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error en reglas de negocio",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>("Error de validacion", HttpStatus.BAD_REQUEST);
    }
}