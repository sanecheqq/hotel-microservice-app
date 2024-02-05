package com.projectsav.bookingapp.controllers;

import com.projectsav.bookingapp.exceptions.*;
import com.projectsav.bookingapp.messages.ExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.StringJoiner;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Object> handleUnauthorizedException(Exception e) {
        return handleExceptionInternal(new RuntimeException("No user exists by provided JWT. Authorize"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RoomIsAlreadyBookedException.class)
    public ResponseEntity<Object> handleAlreadyBookedException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BadDatesParametersException.class,
            BadUserDataException.class
    })
    public ResponseEntity<Object> handleBadParameterException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BookingNotFoundException.class,
            RoomNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
        StringJoiner joiner = new StringJoiner(",");
        e.getConstraintViolations().stream().map(c -> c.getPropertyPath().toString()).forEach(joiner::add);
        return handleExceptionInternal(new RuntimeException(joiner.toString(), e), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex, Object body, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode, @NonNull WebRequest request
    ) {
        return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, status);
    }

}
