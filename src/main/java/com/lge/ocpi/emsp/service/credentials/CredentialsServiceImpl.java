package com.lge.ocpi.emsp.service.credentials;

import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.credentials.partner.PartnerDTO;
import com.lge.ocpi.emsp.model.entity.credentials.PartnerEntity;
import com.lge.ocpi.emsp.repository.PartnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CredentialsServiceImpl implements CredentialsService {


    @Autowired
    PartnerRepository partnerRepository;


    @Override
    public PartnerDTO partnerDetails(PartnerEntity partnerEntity) {
        PartnerEntity partnerEntitys = partnerRepository.save(partnerEntity);
        return CommonMapper.INSTANCE.PartnerfromEntity(partnerEntitys);
    }

    @Transactional
    public void update(Long pid, String roles) {
        partnerRepository
                .findById(pid)
                .ifPresent(credentialsDetails -> {
                    credentialsDetails.setRoles(roles);
                    credentialsDetails.setStatus("REGISTERED");
                    credentialsDetails.setStatus_message("REGISTRATION SUCCESSFUL");
                    partnerRepository.save(credentialsDetails);
                });
    }

}
