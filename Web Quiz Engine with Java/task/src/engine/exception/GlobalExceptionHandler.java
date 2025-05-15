package engine.exception;

import engine.repository.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<Void> handleQuizNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
