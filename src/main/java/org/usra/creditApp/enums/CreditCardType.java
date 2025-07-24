package org.usra.creditApp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public enum CreditCardType {

    SAVINGS_360("SAVINGS_360"),
    CHECKING("CHECKING"),
    BROKERAGE("BROKERAGE"),
    HEALTH_SAVINGS_ACCOUNT("HEALTH_SAVINGS_ACCOUNT");

    private final String displayName;

    CreditCardType(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static CreditCardType fromValue(String value) {
        for (CreditCardType creditCardType : CreditCardType.values()) {
            if (creditCardType.displayName.equalsIgnoreCase(value) || creditCardType.name().equalsIgnoreCase(value.replace(" ","_"))) {
                log.debug("creditCardType is : {}", value);
                return creditCardType;
            }
        }
        throw new IllegalArgumentException("Unknown CreditCardType: " + value);
    }
}
