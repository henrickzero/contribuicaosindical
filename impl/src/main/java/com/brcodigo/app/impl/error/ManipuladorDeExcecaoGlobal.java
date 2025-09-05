package com.brcodigo.app.impl.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class ManipuladorDeExcecaoGlobal {

    @ExceptionHandler(AccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<Object> handleAccessException(AppException ex) {
        log.error("[ACCESS-ERROR] - {}",ex.getMessage(), ex);
        // Aqui você pode customizar o retorno como desejar
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensagem", ex.getMessage());
        // Incluir mais informações se necessário
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ResponseEntity<Object> handleAppException(AppException ex) {
        log.error("[APP-ERROR] - {}",ex.getMessage(), ex);
        // Aqui você pode customizar o retorno como desejar
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensagem", ex.getMessage());
        // Incluir mais informações se necessário
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            log.error("[CLIENT-ERROR]", ex);
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
            errors.put("mensagem", error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("[SERVER-ERROR] - {}",ex.getMessage(),ex);
        // Tratamento genérico de exceções
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensagem", "Opa! Algo não saiu como esperávamos...");
        // Incluir mais detalhes sobre a exceção se desejado
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}