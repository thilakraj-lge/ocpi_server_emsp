package com.lge.ocpi.emsp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lge.ocpi.emsp.util.EnumUtil;

public enum AllowedType {
    /**
     * This Token is allowed to charge (at this location).
     */
    ALLOWED("ALLOWED"),
    /**
     * This Token is blocked.
     */
    BLOCKED("BLOCKED"),
    /**
     * This Token has expired.
     */
    EXPIRED("EXPIRED"),
    /**
     * This Token belongs to an account that has not enough credits to charge (at the given location).
     */
    NO_CREDIT("NO_CREDIT"),
    /**
     * Token is valid, but is not allowed to charge at the given location.
     */
    NOT_ALLOWED("NOT_ALLOWED");
    private final String value;

    AllowedType(String value) {
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
    public static AllowedType fromValue(String value) {
        return EnumUtil.findByField(
                AllowedType.class,
                AllowedType::value,
                value
        );
    }
}
