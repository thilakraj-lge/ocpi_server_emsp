package com.lge.ocpi.emsp.model.dto.Token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lge.ocpi.emsp.util.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Location and EVSEs for which the Token is requested to be authorized.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationReferencesDto {

    /**
     * Unique identifier for the location
     */
    @NotBlank(message = "Location ID cannot be null or empty")
    @Size(min = 1, max = 36)
    @JsonProperty("location_id")
    private String locationId;

    /**
     * Unique identifiers for EVSEs within the CPO’s platform for the EVSE within the
     * given location.
     */
    @JsonProperty("evse_uids")
    private List<@Size(min = 1, max = 36) @Pattern(regexp = Constants.ASCII_REGEXP) String> evseUids;
}
