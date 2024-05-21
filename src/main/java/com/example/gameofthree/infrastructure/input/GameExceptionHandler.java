package com.example.gameofthree.infrastructure.input;

import com.example.gameofthree.domain.exception.GameHasNotBeenStarted;
import com.example.gameofthree.domain.exception.WrongTurnPlayingException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.MULTI_STATUS;

@ControllerAdvice
@Slf4j
public class GameExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler({GameHasNotBeenStarted.class, WrongTurnPlayingException.class})
  public ResponseEntity<Object> handleApplicationException(RuntimeException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    LocalDateTime now = now();
    body.put("timestamp", now);
    body.put("message", ex.getMessage());
    log.debug(String.format("Timestamp: %s Application exceptions: %s", now, ex.getMessage()));
    return new ResponseEntity<>(body, MULTI_STATUS);
  }

  @ExceptionHandler({ValidationException.class})
  public ResponseEntity<Object> handleValidationException(ValidationException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    LocalDateTime now = now();
    body.put("timestamp", now);
    body.put("message", ex.getMessage());
    log.debug(String.format("Timestamp: %s Validation exceptions: %s", now, ex.getMessage()));
    return new ResponseEntity<>(body, MULTI_STATUS);
  }
}
