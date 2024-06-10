package com.lge.ocpi.emsp.model.dto.credentials.credentialResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDetails implements Serializable {
	private String name;
	private String website;
}