package dev.wellington.starwars.planets.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(
          {
            PlanetIdNotFoundException.class,
            PlanetNameNotFoundException.class
          }
  )
  @ResponseStatus(BAD_REQUEST)
  public ResponseEntity<ErrorDetail> handlerPlanetNotFoundException(RuntimeException ex) {
    return new ResponseEntity<>(
            new ErrorDetail(BAD_REQUEST.name(), LocalDateTime.now(), ex.getMessage()), BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public ResponseEntity<ErrorDetail> handlerDefaultHandlerException(MethodArgumentNotValidException ex) {
    List<String> errorMessages = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .toList();

    String messageErrors = String.join("; ", errorMessages);

    return new ResponseEntity<>(
            new ErrorDetail(BAD_REQUEST.name(), LocalDateTime.now(), messageErrors), BAD_REQUEST);
  }
}
