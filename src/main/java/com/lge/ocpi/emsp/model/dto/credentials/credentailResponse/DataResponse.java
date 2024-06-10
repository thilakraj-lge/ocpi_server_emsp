package com.lge.ocpi.emsp.model.dto.credentials.credentailResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse implements Serializable {
	private String token;
	private String url;
	private BusinessDetailsResponse businessDetails;
	private String partyId;
	private String countryCode;
}