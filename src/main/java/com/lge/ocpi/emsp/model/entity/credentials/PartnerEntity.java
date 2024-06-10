package com.lge.ocpi.emsp.model.entity.credentials;


import com.lge.ocpi.emsp.model.enums.VersionNumber;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Type;




@Entity
@Data
@Table(name = "partner")
public class PartnerEntity {

    @NotNull
    @Column(name = "pid")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long pid;
    public String uid;
    public String country_code;
    public String party_id;
    private String status;
    public String status_message;
    public String from_token;


    @Column(columnDefinition = "text")
    public String roles;

    public String created_at;
    public String last_updated;


}
