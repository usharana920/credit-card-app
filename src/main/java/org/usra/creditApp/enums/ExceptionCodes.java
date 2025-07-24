package org.usra.creditApp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCodes {
    CUSTOMER_NOT_FOUND("CORE_1001", "Customer cannot be found.");
    private final String code;
    private final String description;

}
