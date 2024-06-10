package com.lge.ocpi.emsp.model.dto.credentials.credentailResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDetailsResponse implements Serializable {
	private String name;
	private String website;
}