package com.lge.ocpi.emsp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lge.ocpi.emsp.util.EnumUtil;

public enum ParkingType {
    /**
     * Location on a parking facility/rest area along a motorway, freeway, interstate, highway etc.
     */
    ALONG_MOTORWAY("ALONG_MOTORWAY"),
    /**
     * Multistorey car park.
     */
    PARKING_GARAGE("PARKING_GARAGE"),
    /**
     * A cleared area that is intended for parking vehicles, i.e. at super markets, bars, etc.
     */
    PARKING_LOT("PARKING_LOT"),
    /**
     * Location is on the driveway of a house/building.
     */
    ON_DRIVEWAY("ON_DRIVEWAY"),
    /**
     * Parking in public space along a street.
     */
    ON_STREET("ON_STREET"),
    /**
     * Multistorey car park, mainly underground.
     */
    UNDERGROUND_GARAGE("UNDERGROUND_GARAGE");
    private final String value;

    ParkingType(String value) {
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
    public static ParkingType fromValue(String value) {
        return EnumUtil.findByField(
                ParkingType.class,
                ParkingType::value,
                value
        );
    }
}
