package com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDetails implements Serializable {
	private Emsp emsp;
}