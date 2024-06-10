package com.lge.ocpi.emsp.exception;


import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ResponseEntity object
     */

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String missingParamName = ex.getParameterName() + " parameter is missing";
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(OcpiStatusCode.INVALID_PARAMETERS, missingParamName);
        return new ResponseEntity<>(responseFormat, headers, BAD_REQUEST);
    }

    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ResponseEntity object
     */

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String unsupported = "Unsupported content type: " + ex.getContentType();
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(METHOD_NOT_ALLOWED, unsupported);
        return new ResponseEntity<>(responseFormat, headers, status);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ResponseEntity object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(OcpiStatusCode.INVALID_PARAMETERS, errors.toString());
        return new ResponseEntity<>(responseFormat, headers, status);
    }


    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ResponseEntity object
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        String constraintViolations = ex.getConstraintName();

        String error = "Constraint Violation(s):\n" + String.join("\n", constraintViolations);
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(OcpiStatusCode.INVALID_PARAMETERS, error);
        return new ResponseEntity<>(responseFormat, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ResponseEntity object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {

        String entityName = null;

        String exceptionName = ex.getClass().getSimpleName();
        entityName = exceptionName.substring(0, exceptionName.indexOf("NotFoundException"));

        String message = entityName + " with the provided ID was not found.";

        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(NOT_FOUND, message);
        return new ResponseEntity<>(responseFormat, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ResponseEntity object
     */


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, org.springframework.http.HttpHeaders headers, HttpStatusCode status, org.springframework.web.context.request.WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(BAD_REQUEST, "Malformed Json Format");
        return new ResponseEntity<>(responseFormat, headers, BAD_REQUEST);

    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ResponseEntity object
     */

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, org.springframework.http.HttpHeaders headers, HttpStatusCode status, org.springframework.web.context.request.WebRequest request) {
        String error = "Error writing JSON output";
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(INTERNAL_SERVER_ERROR, error);
        return new ResponseEntity<>(error, headers, INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return the ResponseEntity object
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL(), ex.getMessage());
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(NOT_FOUND, error);
        return new ResponseEntity<>(responseFormat, headers, NOT_FOUND);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ResponseEntity object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        ResponseFormat<Object> responseFormat;
        if (ex.getCause() instanceof ConstraintViolationException) {
            responseFormat = new ResponseFormat<>().build(HttpStatus.CONFLICT, "Database error " + ex.getCause());
            return new ResponseEntity<>(responseFormat, HttpStatus.CONFLICT);
        }
        responseFormat = new ResponseFormat<>().build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(responseFormat, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ResponseEntity object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        String error = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName(), ex.getMessage());
        ResponseFormat<Object> responseFormat = new ResponseFormat<>().build(OcpiStatusCode.INVALID_PARAMETERS, error);
        return new ResponseEntity<>(responseFormat, HttpStatus.BAD_REQUEST);
    }



}
