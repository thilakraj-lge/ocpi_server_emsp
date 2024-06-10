package com.lge.ocpi.emsp.model.dto.location;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Connector is the socket or cable and plug available for the EV to use. A single EVSE may provide
 * multiple Connectors but only one of them can be in use at the same time.
 * A Connector always belongs to an EVSE object.
 */
@Data
public class Connector implements Serializable {
    /**
     * Identifier of the Connector within the EVSE. Two Connectors may have
     * the same id as long as they do not belong to the same EVSE object.
     */

    private String id;
    /**
     * The standard of the installed connector.
     */


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private String standard;
    /**
     * The format (socket/cable) of the installed connector.
     */


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private String format;


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private String power_type;
    /**
     * Maximum voltage of the connector (line to neutral for AC_3_PHASE), in volt [V].
     * For example: DC Chargers might vary the voltage during charging when battery almost full.
     */

    private Integer max_voltage;
    /**
     * Maximum amperage of the connector, in ampere [A].
     */

    private Integer max_amperage;
    /**
     * Maximum electric power that can be delivered by this connector, in Watts (W). When the maximum electric power
     * is lower than the calculated value from voltage and amperage, this value should be set.
     * For example: A DC Charge Point which can delivers up to 920V and up to 400A can be limited to a maximum
     * of 150kW (max_electric_power = 150000). Depending on the car, it may supply max voltage or current,
     * but not both at the same time. For AC Charge Points, the amount of phases used can also have influence on the
     * maximum power
     */

    private Integer max_electric_power;
    /**
     * Identifiers of the currently valid charging tariffs. Multiple tariffs are possible, but only one of
     * each Tariff.type can be active at the same time. Tariffs with the same type are only allowed if they
     * are not active at the same time: start_date_time and end_date_time period not overlapping.
     * When preference-based smart charging is supported, one tariff for every possible ProfileType should be provided.
     * These tell the user about the options they have at this Connector, and what the tariff is for every option.
     * For a "free of charge" tariff, this field should be set and point to a defined "free of charge" tariff.
     */


    @Type(JsonType.class)
    private List<String> tariff_ids;
    /**
     * URL to the operator’s terms and conditions.
     */

    private String terms_and_conditions;
    /**
     * Timestamp when this Connector was last updated (or created).
     */

    private LocalDateTime last_updated;
}