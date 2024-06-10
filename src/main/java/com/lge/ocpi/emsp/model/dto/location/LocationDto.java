package com.lge.ocpi.emsp.model.dto.location;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class LocationDto implements Serializable {


    private Long loc_id;


    private String id;


    private String country_code;


    private String party_id;

    private boolean publish;

    @Type(JsonType.class)
    private List<Map<String, Object>> publish_allowed_to;

    /**
     * Display name of the location.
     */
    @Size(min = 1, max = 255)
    private String name;

    /**
     * Street/block name and house number if available.
     */

    @Size(min = 1, max = 45)
    private String address;

    /**
     * City or town.
     */

    @Size(min = 1, max = 45)
    private String city;

    /**
     * Postal code of the location, may only be omitted when the location has no postal code: in some countries charging
     * locations at highways don’t have postal codes.
     */
    @Size(min = 1, max = 10)
    private String postal_code;

    /**
     * State or province of the location, only to be used when relevant.
     */
    @Size(min = 1, max = 20)
    private String state;

    /**
     * ISO 3166-1 alpha-3 code for the country of this location.
     */
    @NotBlank
    @Size(min = 1, max = 3)
    private String country;

    /**
     * Coordinates of the location.
     */
    //private GeoLocation coordinates;
    @Type(JsonType.class)
    private Map<String, String> coordinates;

    /**
     * Geographical location of related points relevant to the user.
     */

    //  private List<AdditionalGeoLocation> related_locations;

    @Type(JsonType.class)
    private List<Map<String, Object>> related_locations;

    /**
     * The general type of parking at the charge point location.
     */


    private String parking_type;

    /**
     * List of EVSEs that belong to this Location.
     */
    private List<EVSE> evses;

    /**
     * Human-readable directions on how to reach the location.
     */
    @Type(JsonType.class)
    private List<Map<String, Object>> directions;

    /**
     * Information of the operator. When not specified, the information retrieved from the Credentials module,
     * selected by the country_code and party_id of this Location, should be used instead.
     */
    //  private BusinessDetails operator;

    @Type(JsonType.class)
    private Map<String, String> operator;

    /**
     * Information of the suboperator if available.
     */


    @Type(JsonType.class)
    private Map<String, String> suboperator;

    /**
     * Information of the owner if available.
     */


    @Type(JsonType.class)
    private Map<String, String> owner;

    /**
     * Optional list of facilities this charging location directly belongs to.
     */


    @Type(JsonType.class)
    private List<String> facilities;

    /**
     * One of IANA tzdata’s TZ-values representing the time zone of
     * the location. Examples: "Europe/Oslo", "Europe/Zurich".
     * (@see a href="http://www.iana.org/time-zones">Time Zones</a>)
     */
    @Size(min = 1, max = 255)
    private String time_zone;

    /**
     * The times when the EVSEs at the location can be accessed for charging.
     */


    @Type(JsonType.class)
    private Map<String, String> opening_times;

    /**
     * Indicates if the EVSEs are still charging outside the opening hours of the location. E.g. when the parking garage
     * closes its barriers over-night, is it allowed to charge till the next morning?
     * Default: true
     */

    private boolean charging_when_closed;

    /**
     * Links to images related to the location such as photos or logos.
     */

    @Type(JsonType.class)
    private List<Map<String, Object>> images;


    /**
     * Details on the energy supplied at this location.
     */


    @Type(JsonType.class)
    private Map<String, String> energy_mix;

    private LocalDateTime last_updated;

}