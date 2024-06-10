package com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endpoints implements Serializable {
	private String cdrs;
	private String credentials;
	private String locations;
	private String commands;
	private String sessions;
	private String tariffs;
	private String tokens;
}