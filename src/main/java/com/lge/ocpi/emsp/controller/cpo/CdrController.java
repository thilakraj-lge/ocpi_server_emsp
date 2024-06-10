package com.lge.ocpi.emsp.controller.cpo;

import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.cdr.CdrDto;
import com.lge.ocpi.emsp.model.entity.cdr.Cdr;
import com.lge.ocpi.emsp.service.cdr.CdrService;
import jakarta.validation.Valid;
import com.lge.ocpi.emsp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.Endponints.emsp_endpoint_url_server+"cdrs")
public class CdrController {
    @Autowired
    private CdrService cdrService;

    @Autowired
    CommonMapper mapper;

    @PostMapping("/{country_code}/{party_id}/{cdr_id}")
    public ResponseEntity<ResponseFormat<CdrDto>> postCdr(@RequestBody @Valid CdrDto cdr,
                                                          @PathVariable(value = "country_code") String countryCode,
                                                          @PathVariable(value = "party_id") String partyId,
                                                          @PathVariable(value = "cdr_id") String cdrId) {
        Cdr cdrEntity = mapper.cdrToEntity(cdr);
        CdrDto cdrDto = cdrService.postCdr(cdrEntity);
        ResponseFormat<CdrDto> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<CdrDto>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<CdrDto>()
                    .build(OcpiStatusCode.SUCCESS, cdrDto);
        }
        return ResponseEntity.ok(responseFormat);

    }

    @GetMapping(value = "/{country_code}/{party_id}/{cdr_id}", produces = "application/json")
    public ResponseEntity<CdrDto> getCdr(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "cdr_id") String cdrId) {
        CdrDto cdrDto = cdrService.getCdr(cdrId);
        return ResponseEntity.ok(cdrDto);
    }
}
