package com.lge.ocpi.emsp.model.dto.credentials.versionDetailsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndpointsListResponse implements Serializable {
	private String identifier;
	private String url;

}