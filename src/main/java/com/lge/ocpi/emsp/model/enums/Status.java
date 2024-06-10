package com.lge.ocpi.emsp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lge.ocpi.emsp.util.EnumUtil;

public enum Status {
    AVAILABLE("AVAILABLE"),
    BLOCKED("BLOCKED"),
    CHARGING("CHARGING"),
    INOPERATIVE("INOPERATIVE"),
    OUTOFORDER("OUTOFORDER"),
    PLANNED("PLANNED"),
    REMOVED("REMOVED"),
    RESERVED("RESERVED"),
    UNKNOWN("UNKNOWN");
    private final String value;

    Status(String value) {
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
    public static Status fromValue(String value) {
        return EnumUtil.findByField(
                Status.class,
                Status::value,
                value
        );
    }
}
