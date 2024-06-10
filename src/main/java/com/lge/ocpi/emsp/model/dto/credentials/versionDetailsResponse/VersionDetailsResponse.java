package com.lge.ocpi.emsp.model.dto.credentials.versionDetailsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDetailsResponse implements Serializable {
	private VersionDataResponse data;
	private int statusCode;
	private String statusMessage;
	private String timestamp;

}