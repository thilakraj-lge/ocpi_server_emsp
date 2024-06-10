package com.lge.ocpi.emsp.model.dto.credentials.credentailResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialResponse implements Serializable {
	private DataResponse data;
	private int statusCode;
	private String statusMessage;
	private String timestamp;
}