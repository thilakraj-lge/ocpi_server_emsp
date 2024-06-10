package com.lge.ocpi.emsp.controller.cpo;


import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.version.VersionDetailsDTO;
import com.lge.ocpi.emsp.model.dto.version.VersionDto;
import com.lge.ocpi.emsp.model.entity.version.VersionDetailsEntity;
import com.lge.ocpi.emsp.model.entity.version.VersionEntity;
import com.lge.ocpi.emsp.model.enums.VersionNumber;
import com.lge.ocpi.emsp.service.version.VersionService;
import jakarta.validation.Valid;
import com.lge.ocpi.emsp.util.Constants;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emsp/api/versions")
public class VersionController {
    @Autowired
    VersionService versionService;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping("/get-versions")
    public ResponseEntity<ResponseFormat<List<VersionDto>>> getVersions() {
        VersionDto versionDto = new VersionDto();
        versionDto.setUrl("http://10.221.61.185:9898/emsp/api/versions/get-version-details?version=2.1.1");
        versionDto.setVersion(VersionNumber.V_2_1_1);

        List<VersionDto> versionDtos = new ArrayList<>();
        versionDtos.add(versionDto);

        ResponseFormat<List<VersionDto>> responseFormat;
        if (versionDtos != null) {

            responseFormat = new ResponseFormat<List<VersionDto>>()
                    .build(OcpiStatusCode.SUCCESS, versionDtos);
        } else {
            responseFormat = new ResponseFormat<List<VersionDto>>()
                    .build(OcpiStatusCode.NOT_ENOUGH_INFORMATION);
        }
        return ResponseEntity.ok(responseFormat);


/*        String data = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"version\": \"2.1.1\",\n" +
                "            \"url\": \"http://10.221.61.136:9898/emsp/api/versions/get-version-details?version=2.1.1\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status_code\": 1000,\n" +
                "    \"status_message\": \"Success\",\n" +
                "    \"timestamp\": \"2024-05-23T09:37:45Z\"\n" +
                "}";

        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        Type resultType = new TypeToken<VersionRequest>() {
        }.getType();
        VersionRequest result = gson.fromJson(data, resultType);
        return result;

       */

    }


    @GetMapping("/get-version-details")
    public ResponseEntity<ResponseFormat<VersionDetailsDTO>> getVersionDetails(@RequestParam(value = "version") String version) {


        // VersionDetailsDto versionDetails = versionService.getVersionDetails(VersionNumber.fromValue(version));
        // VersionDetailsDto versionDetails = versionService.getVersionDetails(VersionNumber.fromValue(version));

        // VersionDetailsDto versionDetailsDto = versionService.getVersionDetails(VersionNumber.valueOf(version));

        VersionDetailsDTO versionDetailsDto = new VersionDetailsDTO();
        versionDetailsDto.setVersion(VersionNumber.V_2_1_1);

       /* ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> data = mapper.readValue(endpointList, new TypeReference<List<Map<String, Object>>>() {
            });
            versionDetailsDto.setEndpoints(data);

            Gson gson = new Gson();
            Type resultType = new TypeToken<List<Map<String, Object>>>(){}.getType();
            List<Map<String, Object>> result = gson.fromJson(endpointList, resultType);
            versionDetailsDto.setEndpoints(result);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }*/


        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        Type resultType = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        List<Map<String, Object>> result = gson.fromJson(Constants.Endponints.endpoint, resultType);
        versionDetailsDto.setEndpoints(result);

        ResponseFormat<VersionDetailsDTO> responseFormat;
        if (versionDetailsDto != null) {
            responseFormat = new ResponseFormat<VersionDetailsDTO>()
                    .build(OcpiStatusCode.SUCCESS, versionDetailsDto);
        } else {
            responseFormat = new ResponseFormat<VersionDetailsDTO>()
                    .build(OcpiStatusCode.NOT_ENOUGH_INFORMATION);
        }
        return ResponseEntity.ok(responseFormat);
    }


    @PutMapping("/save-version-details")
    public ResponseEntity<ResponseFormat<VersionDetailsDTO>> pushVersionDetails(@RequestBody @Valid VersionDetailsDTO versionDetailsDto) {
        VersionDetailsEntity versionDetailsEntity = commonMapper.VersionDetailsToEntity(versionDetailsDto);
        VersionDetailsDTO versionDetails = versionService.putVersionDetails(versionDetailsEntity);
        ResponseFormat<VersionDetailsDTO> responseFormat;
        if (versionDetails != null) {

            responseFormat = new ResponseFormat<VersionDetailsDTO>()
                    .build(OcpiStatusCode.SUCCESS, versionDetails);
        } else {
            responseFormat = new ResponseFormat<VersionDetailsDTO>()
                    .build(OcpiStatusCode.NOT_ENOUGH_INFORMATION);
        }
        return ResponseEntity.ok(responseFormat);

    }

    @PutMapping("/put-version")
    public ResponseEntity<ResponseFormat<VersionDto>> pushVersionDetails(@RequestBody @Valid VersionDto versionDto) {
        VersionEntity versionEntity = commonMapper.VersionToEntity(versionDto);
        VersionDto versionDTO = versionService.putVersion(versionEntity);
        ResponseFormat<VersionDto> responseFormat;
        if (versionDTO != null) {

            responseFormat = new ResponseFormat<VersionDto>()
                    .build(OcpiStatusCode.SUCCESS, versionDTO);
        } else {
            responseFormat = new ResponseFormat<VersionDto>()
                    .build(OcpiStatusCode.NOT_ENOUGH_INFORMATION);
        }
        return ResponseEntity.ok(responseFormat);

    }
}
