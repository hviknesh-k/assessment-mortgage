package org.ing.api.mortgages.controller.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.ing.api.mortgages.exception.ErrorCode;
import org.ing.api.mortgages.exception.MortgageServiceException;
import org.ing.api.mortgages.exception.MortgageValidationException;
import org.openapitools.model.Error;
import org.openapitools.model.MortgageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MortgageServiceException.class)
    public ResponseEntity<Error> handleApplicationException(MortgageServiceException exception) {
        log.error("MortgageServiceException : {}", exception.getErrorCode().getMessage());
        return buildApiErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(MortgageValidationException.class)
    public ResponseEntity<MortgageResponse> handleMortgageValidationException(MortgageValidationException exception) {
        log.error("MortgageValidationException : {}", exception.getErrorCode().getMessage());
        return buildMortgageIneligibleApiResponse(exception.getErrorCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> constraintViolationException(ConstraintViolationException exception) {
        log.error("Validation Exception : {}", exception.getMessage());
        return buildApiErrorResponse(ErrorCode.INVALID_INPUT);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Invalid Method Argument Exception : {}", exception.getMessage());
        return buildApiErrorResponse(ErrorCode.INVALID_INPUT);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleUncaughtException(Exception exception) {
        log.error("Uncaught Exception : {}", exception.getMessage());
        return buildApiErrorResponse(ErrorCode.SERVER_ERROR);
    }

    private Error.ErrorBuilder getErrorBuilder(ErrorCode errorCode) {
        return Error.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage());
    }

    private ResponseEntity<Error> buildApiErrorResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus().value())
                .body(getErrorBuilder(errorCode).build());
    }

    private ResponseEntity<MortgageResponse> buildMortgageIneligibleApiResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus().value())
                .body(MortgageResponse.builder().eligible(false).build());
    }


}
