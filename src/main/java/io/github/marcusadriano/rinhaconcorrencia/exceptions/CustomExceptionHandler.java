package io.github.marcusadriano.rinhaconcorrencia.exceptions;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class, DatabindException.class})
    public ResponseEntity<Object> handleBadRequest(final Exception ex) {
        log.error("Error: {}", ex.getMessage());
        return ResponseEntity.unprocessableEntity().build();
    }
}
