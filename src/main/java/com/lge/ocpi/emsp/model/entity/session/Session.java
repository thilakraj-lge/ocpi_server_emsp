package com.lge.ocpi.emsp.model.entity.session;

import com.lge.ocpi.emsp.model.enums.AuthMethod;
import com.lge.ocpi.emsp.model.enums.SessionStatus;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Data
@Table(name = "session")
public class Session {
    @NotNull
    @Column(name = "country_code")
    private String countryCode;

    @NotNull
    @Column(name = "party_id")
    private String partyId;

    @NotNull
    @Id
    @Column(name = "session_id")
    private Long sessionId;

    @NotNull
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @NotNull
    @Column(name = "kwh")
    private Double kwh;

    @NotNull
    @Size(max = 36)
    @Column(name = "cdr_token_id")
    private String cdrTokenId;

    @NotNull
    @Column(name = "auth_method")
    @Enumerated(EnumType.STRING)
    private AuthMethod authMethod;

    @Size(min = 1, max = 36)
    @Column(name = "authorization_reference")
    private String authorizationReference;

    @NotBlank
    @Size(min = 1, max = 36)
    @Column(name = "location_id")
    private String locationId;

    @NotBlank
    @Size(min = 1, max = 36)
    @Column(name = "evse_uid")
    private String evseUid;

    @NotBlank
    @Size(max = 36)
    @Column(name = "connector_id")
    private String connectorId;

    @Size(min = 1, max = 255)
    @Column(name = "meter_id")
    private String meterId;

    @NotBlank
    @Size(min = 1, max = 3)
    @Column(name = "currency")
    private String currency;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_session_id")
    private List<ChargingPeriod> chargingPeriods;

    @Column(name = "total_cost", columnDefinition = "json")
    @Type(JsonType.class)
    private Map<String,Object> totalCost = new HashMap<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SessionStatus status;

    @NotNull
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
