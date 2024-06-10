package com.lge.ocpi.emsp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lge.ocpi.emsp.util.EnumUtil;

/**
 * Most modules (except Credentials, Registration) are optional, but there might be dependencies between modules.
 */
public enum ModuleID {
    CDRS("cdrs"),
    CHARGING_PROFILES("chargingprofiles"),
    COMMANDS("commands"),
    /**
     * Required for all implementations.
     * The role field has no function for this module.
     */
    CREDENTIALS("credentials"),
    HUB_CLIENT_INFO("hubclientinfo"),
    LOCATIONS("locations"),
    SESSIONS("sessions"),
    TARIFFS("tariffs"),
    TOKENS("tokens");
    private final String value;

    ModuleID(String value) {
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
    public static ModuleID fromValue(String value) {
        return EnumUtil.findByField(
                ModuleID.class,
                ModuleID::value,
                value
        );
    }
}
