package com.kingof0.jwtprojecttemplate.security;

import com.kingof0.jwtprojecttemplate.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
class RestExceptionHandling {

    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleAppException(AppException e) {
        return ResponseEntity.status(e.getCode())
                .body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleAppException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleAppException(AuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleAppException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorDTO(e.getMessage() + " | Check your path or request method!"));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleAppException(MaxUploadSizeExceededException e) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(new ErrorDTO("File size is too large!"));
    }

    @ExceptionHandler(StorageException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleStorageFileNotFound(StorageException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(FileServeException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleFileServeException(FileServeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}