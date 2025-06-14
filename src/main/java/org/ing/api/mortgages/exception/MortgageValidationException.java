package org.ing.api.mortgages.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class MortgageValidationException extends MortgageServiceException{
    public MortgageValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}