package com.lge.ocpi.emsp.model.entity.session;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Data
@ToString
@Table(name = "charging_periods")
public class ChargingPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "charging_period_id")
    private Long id;

    @Column(name = "start_date_time")
    @NotNull
    private LocalDateTime startDateTime;

    @NotEmpty
    @Type(JsonType.class)
    @Column(name = "dimensions", columnDefinition = "json")
    private List<Map<String,Object>> dimensions = new ArrayList<Map<String,Object>>();

    @Column(name = "tariff_id")
    @NotNull
    private String tariffId;
}
