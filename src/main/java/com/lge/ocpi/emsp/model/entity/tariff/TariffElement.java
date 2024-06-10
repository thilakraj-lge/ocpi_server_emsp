package com.lge.ocpi.emsp.model.entity.tariff;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "tariff_element")
public class TariffElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tariff_element_id")
    private Long id;
    /**
     * List of price components that describe the pricing of a tariff.
     */
    @NotEmpty(message = "Price component cannot be empty")
    @Type(JsonType.class)
    @Column(name = "price_components",columnDefinition = "json")
    private List<Map<String,Object>> priceComponents = new ArrayList<>();
    /**
     * Restrictions that describe the applicability of a tariff.
     */
    @Type(JsonType.class)
    @Column(name = "restrictions",columnDefinition = "json")
    private Map<String,Object> restrictions = new HashMap<>();
}
