package com.lge.ocpi.emsp.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static com.lge.ocpi.emsp.util.EnumUtil.findByField;


public enum EnvironmentalImpactCategory {
    /**
     * Produced nuclear waste in grams per kilowatt/hour.
     */
    NUCLEAR_WASTE("NUCLEAR_WASTE"),
    /**
     * Exhausted carbon dioxide in grams per kilowatt/hour.
     */
    CARBON_DIOXIDE("CARBON_DIOXIDE");
    private final String value;

    EnvironmentalImpactCategory(String value) {
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
    public static EnvironmentalImpactCategory fromValue(String value) {
        return findByField(
                EnvironmentalImpactCategory.class,
                EnvironmentalImpactCategory::value,
                value
        );
    }
}
