package com.lge.ocpi.emsp.model.entity.tariff;

import com.lge.ocpi.emsp.model.enums.TariffType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "tariff")
public class Tariff {
    /**
     *  ISO-3166 alpha-2 country code of the CPO that owns this Tariff.
     */
    @NotNull(message = "Country code cannot be null")
    @Column(name = "country_code")
    private String countryCode;

    /**
     *  ID of the CPO that 'owns' this Tariff (following the ISO-15118 standard).
     */
    @NotNull(message = "Party ID cannot be null")
    @Column(name = "party_id")
    private String partyId;

    /**
     *  Uniquely identifies the tariff within the CPO’s platform (and suboperator
     * platforms).
     */
    @Id
    @NotNull(message = "Tariff ID cannot be null")
    @Column(name = "tariff_id")
    private String tariffId;

    /**
     * ISO-4217 code of the currency of this tariff.
     */
    @NotBlank(message = "Currency can not be empty")
    @Size(min = 1, max = 3)
    @Column(name = "currency")
    private String currency;

    /**
     * Defines the type of the tariff. This allows for distinction in case of given Charging Preferences.
     * When omitted, this tariff is valid for all sessions.
     */
    @Column(name = "type")
    private TariffType type;

    /**
     * List of multi-language alternative tariff info texts
     */
    @Type(JsonType.class)
    @Column(name = "tariff_alt_text",columnDefinition = "json")
    private List<Map<String,Object>> tariffAltText = new ArrayList<Map<String,Object>>();

    /**
     * List of multi-language alternative tariff info texts
     */
    @Column(name = "tariff_alt_url")
    private String tariffAltUrl;

    /**
     * When this field is set, a Charging Session with this tariff will at least cost this
     * amount. This is different from a FLAT fee (Start Tariff, Transaction Fee), as a
     * FLAT fee is a fixed amount that has to be paid for any Charging Session. A
     * minimum price indicates that when the cost of a Charging Session is lower than
     * this amount, the cost of the Session will be equal to this amount.
     */

    @Type(JsonType.class)
    @Column(name = "min_price",columnDefinition = "json")
    private Map<String,Object> minPrice = new HashMap<>();

    /**
     * When this field is set, a Charging Session with this tariff will NOT cost more than
     * this amount.
     */

    @Type(JsonType.class)
    @Column(name = "max_price",columnDefinition = "json")
    private Map<String,Object> maxPrice = new HashMap<>();

    /**
     * List of Tariff Elements.
     */
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tariff_id")
    private List<TariffElement> elements;

    /**
     * The time when this tariff becomes active, in UTC, time_zone field of the
     * Location can be used to convert to local time. Typically used for a new tariff that
     * is already given with the location, before it becomes active.
     */
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    /**
     * The time after which this tariff is no longer valid, in UTC, time_zone field if the
     * Location can be used to convert to local time. Typically used when this tariff is
     * going to be replaced with a different tariff in the near future. (See note below)
     */
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    /**
     * Details on the energy supplied with this tariff.
     */
    @Type(JsonType.class)
    @Column(name = "energy_mix",columnDefinition = "json")
    private Map<String,Object> energyMix = new HashMap<>();

    @NotNull(message = "Last updated cannot be null")
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
