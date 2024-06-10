package com.lge.ocpi.emsp.model.dto.credentials.credentialResponseBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialResponseBody implements Serializable {
	private Data data;
	private int status_code;
	private String status_message;
	private String timestamp;
}