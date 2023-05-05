package com.bell.kst.exception;

import com.bell.kst.constants.StatusCode;
import com.bell.kst.dto.StatusCodeDTO;
import org.hibernate.exception.LockAcquisitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String requestURI = request.getRequestURI();

        String errorMessage = "Exception occurred while processing the endpoint for " + requestURI + " : " + e.getClass().getSimpleName();
        logger.error(errorMessage, e);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof LockAcquisitionException) {
            status = HttpStatus.CONFLICT;
        } else if (e instanceof UsernameNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (e instanceof NonUniqueResultException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (e instanceof DataIntegrityViolationException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (e instanceof TransactionException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (e instanceof NoResultException) {
            status = HttpStatus.NOT_FOUND;
        } else if (e instanceof TypeMismatchException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (e instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (e instanceof PersistenceException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        Map<String, Object> responseMap = new HashMap<>();

        switch (status.value()) {
            case 409:
                responseMap.put("status", new StatusCodeDTO(StatusCode.CONFLICT.getCode(), StatusCode.CONFLICT.getMessage()));
                break;
            case 404:
                responseMap.put("status", new StatusCodeDTO(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage()));
                break;
            case 400:
                responseMap.put("status", new StatusCodeDTO(StatusCode.BAD_REQUEST.getCode(), StatusCode.BAD_REQUEST.getMessage()));
                break;
            default:
                responseMap.put("status", new StatusCodeDTO(StatusCode.INTERNAL_SERVER_ERROR.getCode(), StatusCode.INTERNAL_SERVER_ERROR.getMessage()));
                break;
        }
        return ResponseEntity.status(status).body(responseMap);
    }
}