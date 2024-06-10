package com.lge.ocpi.emsp.model.entity.location;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "connector")
public class ConnectorEntity implements Serializable {


    @Id
    @Column(name = "con_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int con_id;

    private String id;


    @Type(JsonType.class)
    @Column(columnDefinition = "longtext")
    private String standard;


    @Type(JsonType.class)
    @Column(columnDefinition = "longtext")
    private String format;


    @Type(JsonType.class)
    @Column(columnDefinition = "longtext")
    private String power_type;

    private int max_voltage;
    private int max_amperage;
    private Integer max_electric_power;


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<String> tariff_ids;


    private LocalDateTime last_updated;
    private String terms_and_conditions;


}