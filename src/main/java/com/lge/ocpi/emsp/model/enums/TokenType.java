package com.lge.ocpi.emsp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lge.ocpi.emsp.util.EnumUtil;

public enum TokenType {
    /**
     * One time use Token ID generated by a server (or App.) The eMSP uses this to bind a Session to a
     * customer, probably an app user.
     */
    AD_HOC_USER("AD_HOC_USER"),
    /**
     * Token ID generated by a server (or App.) to identify a user of an App. The same user uses the
     * same Token for every Session.
     */
    APP_USER("APP_USER"),
    /**
     * Other type of token
     */
    OTHER("OTHER"),
    /**
     * RFID Token
     */
    RFID("RFID");
    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static TokenType fromValue(String value) {
        return EnumUtil.findByField(
                TokenType.class,
                TokenType::value,
                value
        );
    }
}
