package com.lge.ocpi.emsp.model.dto.credentials.credentialResponseBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data implements Serializable {
	private String token;
	private String url;
	private BusinessDetails business_details;
	private String party_id;
	private String country_code;
}