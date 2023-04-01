package com.example.springstudentservice.exception.handler;

import com.example.springstudentservice.exception.model.Error;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        String message = errors.stream().collect(Collectors.joining(", "));
        Error error = new Error();
        error.setMessage(message);
        error.setDescription(ex.getClass().getName()
                + " in "+ ex.getStackTrace()[0].getClassName() +" at line number: " + ex.getStackTrace()[0].getLineNumber());
        return new ResponseEntity<Object>(error, error.getStatus());
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, NoSuchElementException.class})
    public final ResponseEntity<Object> handle(Exception ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);

        if(ex.getClass().equals(DataIntegrityViolationException.class)) {
            error.setMessage("Entry already exists");
            error.setStatus(HttpStatus.CONFLICT);
        }
        error.setMessage(ex.getMessage());
        error.setDescription(ex.getClass().getName()
                + " in "+ ex.getStackTrace()[0].getClassName() +" at line number: " + ex.getStackTrace()[0].getLineNumber());
        return new ResponseEntity<Object>(error, error.getStatus());
    }

}
