package com.lge.ocpi.emsp.model.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lge.ocpi.emsp.util.EnumUtil;

/**
 * This value, if provided, represents the restriction to the parking spot for different purposes.
 */
public enum ParkingRestriction  {
    /**
     * Reserved parking spot for electric vehicles.
     */
    EV_ONLY("EV_ONLY"),
    /**
     * Reserved parking spot for electric vehicles.
     */
    PLUGGED("PLUGGED"),
    /**
     * Reserved parking spot for electric vehicles.
     */
    DISABLED("DISABLED"),
    /**
     * Reserved parking spot for electric vehicles.
     */
    CUSTOMERS("CUSTOMERS"),
    /**
     * Parking spot only suitable for (electric) motorcycles or scooters.
     */
    MOTORCYCLES("MOTORCYCLES");
    private final String value;

    ParkingRestriction(String value) {
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
    public static ParkingRestriction fromValue(String value) {
        return EnumUtil.findByField(
                ParkingRestriction.class,
                ParkingRestriction::value,
                value
        );
    }
}
