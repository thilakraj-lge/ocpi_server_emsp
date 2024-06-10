package com.lge.ocpi.emsp.controller.cpo;


import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.tariff.TariffDto;
import com.lge.ocpi.emsp.model.entity.tariff.Tariff;
import com.lge.ocpi.emsp.service.tariff.TariffService;
import jakarta.validation.Valid;
import com.lge.ocpi.emsp.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(Constants.Endponints.emsp_endpoint_url_server + "tariffs")
public class TariffController {

    @Autowired
    private TariffService tariffService;
    @Autowired
    CommonMapper mapper;

    @PutMapping("/{country_code}/{party_id}/{tariff_id}")
    public ResponseEntity<ResponseFormat<TariffDto>> pushTariff(@RequestBody @Valid TariffDto tariffDto,
                                                               @PathVariable(value = "country_code") String countryCode,
                                                               @PathVariable(value = "party_id") String partyId,
                                                               @PathVariable(value = "tariff_id") String tariffId) {
        Tariff tarrif = mapper.tariffToEntity(tariffDto);
        TariffDto tariff= tariffService.pushTariff(tarrif);
        ResponseFormat<TariffDto> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<TariffDto>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<TariffDto>()
                    .build(OcpiStatusCode.SUCCESS, tariff);
        }
        return ResponseEntity.ok(responseFormat);

    }

    @GetMapping(value = "/{country_code}/{party_id}/{tariff_id}", produces = "application/json")
    public ResponseEntity<TariffDto> getTariff(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "tariff_id") String tariffId) {
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        return ResponseEntity.ok(tariffDto);
    }

    @DeleteMapping(value = "/{country_code}/{party_id}/{tariff_id}", produces = "application/json")
    public ResponseEntity<String> deleteTariff(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "tariff_id") String tariffId) {
        tariffService.deleteTariff(tariffId);
        return ResponseEntity.ok("Deleted");
    }
}
