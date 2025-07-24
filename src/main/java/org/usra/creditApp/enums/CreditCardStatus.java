package org.usra.creditApp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public enum CreditCardStatus {
    CARD_APPROVED("APPROVED"),
    CARD_DENIED("DENIED"),
    CARD_UNDER_REVIEW("UNDER_REVIEW"),
    CARD_REQUEST_RECEIVED("REQUEST_RECEIVED");

    private final String displayName;

    CreditCardStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static CreditCardStatus fromValue(String value) {
        for (CreditCardStatus creditCardStatus : CreditCardStatus.values()) {
            if (creditCardStatus.displayName.equalsIgnoreCase(value) || creditCardStatus.name().equalsIgnoreCase(value.replace(" ","_"))) {
                log.debug("CreditCardStatus is : {}", creditCardStatus);
                return creditCardStatus;
            }
        }
        throw new IllegalArgumentException("Unknown CreditCardStatus: " + value);
    }
}
