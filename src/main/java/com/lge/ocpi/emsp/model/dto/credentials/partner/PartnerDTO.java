package com.lge.ocpi.emsp.model.dto.credentials.partner;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO {

    public Long pid;
    public String uid;
    public String country_code;
    public String party_id;
    public String status;
    public String status_message;
    public String from_token;
    public String roles;
    public String created_at;
    public String last_updated;
}
