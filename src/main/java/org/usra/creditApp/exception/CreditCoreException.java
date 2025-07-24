package org.usra.creditApp.exception;

import lombok.Data;
import org.usra.creditApp.enums.ExceptionCodes;

@Data
public class CreditCoreException extends RuntimeException {
    private final String errorCode;
    private final String errorDescription;

    public CreditCoreException(String errorCode, String errorDescription){
        super();
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public static CreditCoreException asBudgetException(ExceptionCodes exceptionCodes){
        return new CreditCoreException(exceptionCodes.getCode(), exceptionCodes.getDescription());
    }

    public static CreditCoreException asBudgetException(String errorCode, String errorDescription){
        return new CreditCoreException(errorCode, errorDescription);
    }
}
