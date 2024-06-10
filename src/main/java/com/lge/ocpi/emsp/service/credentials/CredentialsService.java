package com.lge.ocpi.emsp.service.credentials;

import com.lge.ocpi.emsp.model.dto.credentials.partner.PartnerDTO;
import com.lge.ocpi.emsp.model.entity.credentials.PartnerEntity;

public interface CredentialsService {

    PartnerDTO partnerDetails(PartnerEntity partnerEntity);
    void update(Long pid, String partnerEntity);

}
