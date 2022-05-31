package com.sprint05.common.exception.handler;

import com.sprint05.common.exception.handler.notfound.NotFoundException;
import com.sprint05.common.exception.model.ErrorObject;
import com.sprint05.common.exception.model.ErrorResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = NOT_FOUND)
    public Mono<ErrorResponse> handlerNotFoundException(NotFoundException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(NOT_FOUND.name())
                        .field(error.getMessage())
                        .build()))
                .build());

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = INTERNAL_SERVER_ERROR)
    public Mono<ErrorResponse> handlerException(Exception error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(INTERNAL_SERVER_ERROR.name())
                        .field(error.getMessage())
                        .build()))
                .build());

    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public Mono<ErrorResponse> methodNotAllowed(MethodNotAllowedException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(INTERNAL_SERVER_ERROR.name())
                        .field(error.getMessage())
                        .build()))
                .build());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public Mono<ErrorResponse> handlerBadRequestException(MethodArgumentNotValidException error) {
        List<FieldError> errorList = error.getBindingResult().getFieldErrors();
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(errorList.stream().map(fieldError -> ErrorObject.builder()
                        .message(fieldError.getDefaultMessage())
                        .field(fieldError.getField()).build()).collect(toList()))
                .build());

    }
}
