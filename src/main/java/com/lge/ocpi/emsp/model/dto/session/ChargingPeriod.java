package com.lge.ocpi.emsp.model.dto.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChargingPeriod {

    /**
     * Start timestamp of the charging period. A period ends when the next period starts.
     * The last period ends when the session ends.
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("start_date_time")
    @NotNull
    private LocalDateTime startDateTime;

    /**
     * List of relevant values for this charging period.
     */
    @NotEmpty
    @JsonProperty("dimensions")
    @Type(JsonType.class)
    private List<Map<String,Object>> dimensions = new ArrayList<Map<String,Object>>();

    /**
     * Unique identifier of the Tariff that is relevant for this Charging Period. If not provided,
     * no Tariff is relevant during this period.
     */
    @JsonProperty("tariff_id")
    private String tariffId;

}
