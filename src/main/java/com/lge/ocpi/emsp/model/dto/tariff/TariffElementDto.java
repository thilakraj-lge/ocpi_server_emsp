package com.lge.ocpi.emsp.model.dto.tariff;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffElementDto {
    /**
     * List of price components that describe the pricing of a tariff.
     */
    @NotEmpty(message = "Price component cannot be empty")
    @JsonProperty("price_components")
    @Type(JsonType.class)
    private List<Map<String,Object>> priceComponents = new ArrayList<>();
    /**
     * Restrictions that describe the applicability of a tariff.
     */
    @Type(JsonType.class)
    private Map<String,Object> restrictions = new HashMap<>();
}
