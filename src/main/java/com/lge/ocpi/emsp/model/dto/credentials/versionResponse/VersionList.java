package com.lge.ocpi.emsp.model.dto.credentials.versionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionList implements Serializable {
	private String version;
	private String url;
}