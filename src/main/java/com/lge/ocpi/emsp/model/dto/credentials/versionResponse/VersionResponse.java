package com.lge.ocpi.emsp.model.dto.credentials.versionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionResponse implements Serializable {
	private List<VersionList> data;
	private int statusCode;
	private String statusMessage;
	private String timestamp;
}