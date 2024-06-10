package com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emsp implements Serializable {
	private String url;
	private String to_token;
	private BusinessDetails business_details;
	private Endpoints endpoints;
}