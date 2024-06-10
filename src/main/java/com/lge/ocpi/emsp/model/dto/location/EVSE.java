package com.lge.ocpi.emsp.model.dto.location;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * The EVSE object describes the part that controls the power supply to a single EV in a single session.
 * It always belongs to a Location object. The object only contains directions to get from the location itself to the
 * EVSE (i.e. floor, physical_reference or directions).
 * When the directional properties of an EVSE are insufficient to reach the EVSE from the Location point,
 * then it typically indicates that the EVSE should be put in a different Location object
 * (sometimes with the same address but with different coordinates/directions).
 */
@Data
public class EVSE implements Serializable {
    /**
     * Uniquely identifies the EVSE within the CPOs platform (and sub-operator platforms). For example a
     * database ID or the actual "EVSE ID". This field can never be changed, modified or renamed.
     * This is the 'technical' identification of the EVSE, not to be used as 'human-readable' identification,
     * use the field evse_id for that. This field is named uid instead of id, because id could be confused
     * with evse_id which is an eMI3 defined field.
     */

    private String uid;
    /**
     * Compliant with the following specification for EVSE ID from "eMI3 standard version V1.0"
     * (<a href="http://emi3group.com/documents-links/"/>) "Part 2: business objects." Optional because: if an evse_id is
     * to be re-used in the real world, the evse_id can be removed from an EVSE object if the status is set to REMOVED.
     */

    private String evse_id;
    /**
     * Indicates the current status of the EVSE.
     */


    private String status;
    /**
     * Indicates a planned status update of the EVSE.
     */


    @Type(JsonType.class)
    private List<Map<String, Object>> status_schedule;
    /**
     * List of functionalities that the EVSE is capable of.
     */


    @Type(JsonType.class)
    private List<String> capabilities;
    /**
     * List of available connectors on the EVSE.
     */
    @NotEmpty
    private List<Connector> connectors;


    /**
     * Level on which the Charge Point is located (in garage buildings) in the
     * locally displayed numbering scheme.
     */
    @Size(max = 4)
    private String floor_level;
    /**
     * Coordinates of the EVSE.
     */


    @Type(JsonType.class)
    private Map<String, String> coordinates;
    /**
     * A number/string printed on the outside of the EVSE for visual
     * identification.
     */
    @Size(max = 16)
    private String physical_reference;
    /**
     * Multi-language human-readable directions when more detailed information on how to reach the EVSE from
     * the Location is required.
     */


    @Type(JsonType.class)
    private List<Map<String, Object>> directions;
    /**
     * The restrictions that apply to the parking spot.
     */


    @Type(JsonType.class)
    private List<Map<String, Object>> parking_restrictions;
    /**
     * Links to images related to the EVSE such as photos or logos.
     */


    @Type(JsonType.class)
    private List<Map<String, Object>> images;
    /**
     * Timestamp when this EVSE or one of its Connectors was last updated (or created).
     */

    private LocalDateTime last_updated;
}


