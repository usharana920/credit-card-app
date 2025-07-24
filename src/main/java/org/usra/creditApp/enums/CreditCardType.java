package org.usra.creditApp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public enum CreditCardType {

    SAVINGS_360("SAVINGS_360"),
    REFLECT("REFLECT"),
    CASH_REWARDS("CASH_REWARDS"),
    TRAVEL_REWARDS("TRAVEL_REWARDS"),
    STANDARD_CASH("STANDARD_CASH"),
    PLATINUM("PLATINUM");

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
