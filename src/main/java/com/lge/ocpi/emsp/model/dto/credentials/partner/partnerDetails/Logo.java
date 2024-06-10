package com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logo implements Serializable {
	private String url;
	private String thumbnail;
	private String category;
	private String type;
	private int width;
	private int height;
}