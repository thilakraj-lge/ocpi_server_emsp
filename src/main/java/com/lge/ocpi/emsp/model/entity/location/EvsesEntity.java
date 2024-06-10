package com.lge.ocpi.emsp.model.entity.location;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "evses")
public class EvsesEntity implements Serializable {
    @Id
    @Column(name = "ev_id")
    private String uid;

   /* @Id
    @Column(name = "ev_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ev_id;*/


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> status_schedule = new ArrayList<Map<String, Object>>();


    private String evse_id;
    private String status;


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<String> capabilities = new ArrayList<String>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_ev_id")
    private List<ConnectorEntity> connectors;


    private String physical_reference;
    private String floor_level;
    private LocalDateTime last_updated;


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> coordinates = new HashMap<>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> directions = new ArrayList<Map<String, Object>>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> parking_restrictions = new ArrayList<Map<String, Object>>();


}