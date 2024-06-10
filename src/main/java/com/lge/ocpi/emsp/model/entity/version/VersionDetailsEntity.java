package com.lge.ocpi.emsp.model.entity.version;


import com.lge.ocpi.emsp.model.enums.VersionNumber;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "versionDetails")
public class VersionDetailsEntity {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    /**
     * The version number.
     */
    @NotNull(message = "Version number cannot be null")
    @Column(name = "versionNumber")
    private VersionNumber version;

    /**
     * A list of supported endpoints for this version.
     */
/*    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private List<Endpoint> endpoints;*/

    @NotEmpty(message = "Endpoints cannot be empty")
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private List<Map<String, Object>> endpoints = new ArrayList<Map<String, Object>>();
}
