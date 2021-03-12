package com.personal.extractorsitesapi.exceptionHandler;

import com.personal.extractorsitesapi.service.exception.PersonNotPresentOrNotActiveException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler{

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMessage = messageSource.getMessage("message.incorrect_field", null, LocaleContextHolder.getLocale());
        String devMessage = ex.getMessage();
        return handleExceptionInternal(ex, new Error(userMessage, devMessage), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = buildErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> buildErrors(BindingResult bindResult) {
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : bindResult.getFieldErrors()) {
            errors.add(new Error(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
                    fieldError.toString()));
        }
        return errors;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String userMessage = messageSource.getMessage("message.not_found", null, LocaleContextHolder.getLocale());
        String devMessage = ex.getMessage();
        return handleExceptionInternal(ex, new Error(userMessage, devMessage), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,  WebRequest request) {
        String userMessage = messageSource.getMessage("message.operation_not_found", null, LocaleContextHolder.getLocale());
        String devMessage = ExceptionUtils.getRootCauseMessage(ex);
        return handleExceptionInternal(ex, new Error(userMessage, devMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PersonNotPresentOrNotActiveException.class)
    public ResponseEntity<Object> handlePersonNotPresentOrNotActiveException(PersonNotPresentOrNotActiveException ex,  WebRequest request) {
        String userMessage = messageSource.getMessage("person.not_present_or_not_active", null, LocaleContextHolder.getLocale());
        String devMessage = ex.toString();
        return handleExceptionInternal(ex, new Error(userMessage, devMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    public static class Error {
        private final String userMessage;
        private final String devMessage;

        private Error(String userMessage, String devMessage) {
            this.userMessage = userMessage;
            this.devMessage = devMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDevMessage() {
            return devMessage;
        }
    }
}
