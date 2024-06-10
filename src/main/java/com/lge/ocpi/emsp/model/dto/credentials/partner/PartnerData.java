package com.lge.ocpi.emsp.model.dto.credentials.partner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerData implements Serializable {
	private String role;
	private String url;
	private String token;
	private String country_code;
	private String party_id;
}