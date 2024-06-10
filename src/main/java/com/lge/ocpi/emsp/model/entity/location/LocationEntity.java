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
@Table(name = "location")
public class LocationEntity implements Serializable {


    /*@Column(name = "loc_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loc_id;*/

    private String country_code;
    private String party_id;

    @Id
    @Column(name = "id")
    private String id;

    private boolean publish;
    private String name;
    private String address;
    private String city;
    private String postal_code;
    private String country;
    private String state;


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> coordinates = new HashMap<>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> related_locations = new ArrayList<Map<String, Object>>();


    @Type(JsonType.class)
    @Column(columnDefinition = "longtext")
    private String parking_type;


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> opening_times = new HashMap<>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> directions = new ArrayList<Map<String, Object>>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_location_id")
    private List<EvsesEntity> evses;


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> operator = new HashMap<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> suboperator = new HashMap<>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> owner = new HashMap<>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<String> facilities = new ArrayList<String>();


    private String time_zone;
    private LocalDateTime last_updated;




    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> energy_mix = new HashMap<>();


    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> publish_allowed_to = new ArrayList<Map<String, Object>>();

    @Column(nullable = false)
    private boolean charging_when_closed;


}