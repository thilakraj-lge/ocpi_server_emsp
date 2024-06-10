package com.lge.ocpi.emsp.controller.customer;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lge.ocpi.emsp.model.dto.credentials.credentialRequest.CredentialRequest;
import com.lge.ocpi.emsp.model.dto.credentials.credentialRequest.Logo;
import com.lge.ocpi.emsp.model.dto.credentials.credentialResponseBody.CredentialResponseBody;
import com.lge.ocpi.emsp.model.dto.credentials.partner.PartnerDTO;
import com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails.BusinessDetails;
import com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails.Emsp;
import com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails.Endpoints;
import com.lge.ocpi.emsp.model.dto.credentials.partner.partnerDetails.PartnerDetails;
import com.lge.ocpi.emsp.model.dto.credentials.versionDetailsResponse.EndpointsListResponse;
import com.lge.ocpi.emsp.model.dto.credentials.versionDetailsResponse.VersionDetailsResponse;
import com.lge.ocpi.emsp.model.dto.credentials.versionResponse.VersionResponse;
import com.lge.ocpi.emsp.model.dto.credentials.partner.PartnerData;
import com.lge.ocpi.emsp.model.entity.credentials.PartnerEntity;
import com.lge.ocpi.emsp.service.credentials.CredentialsService;
import com.lge.ocpi.emsp.service.version.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer/partners")
public class PartnerController {
    String succesResponse;
    List<EndpointsListResponse> list = new ArrayList<>();

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/start")
    public ResponseEntity<String> postCustomer(@RequestBody PartnerData partnerData) throws Exception {
        String url = partnerData.getUrl();
        String token = partnerData.getToken();
        String country_code = partnerData.getCountry_code();
        String party_id = partnerData.getParty_id();

        VersionResponse versionDtoList = getVersions(url, token);
        if (versionDtoList != null) {
            versionDtoList.getData().forEach(
                    (version) -> {
                        if (version.getVersion().equals("2.1.1")) {
                            VersionDetailsResponse versionDetailsDto = getVersionsDetails(version.getUrl(), token);
                           /* VersionDetailsEntity versionDetails = CommonMapper.INSTANCE.VersionAllDetailsToEntity(versionDetailsDto.getData());
                            versionService.putVersionDetails(versionDetails);*/


                            log.info("" + "jdjdd");


                            if (versionDetailsDto != null) {
                                log.info("" + versionDetailsDto.getData().toString());
                                versionDetailsDto.getData().getEndpoints().forEach(
                                        (endpoints) -> {
                                            log.info("" + endpoints.getIdentifier());

                                            if (endpoints.getIdentifier().equals("credentials")) {
                                                String tokenCreated = NanoIdUtils.randomNanoId();
                                                PartnerEntity partnerEntity = new PartnerEntity();
                                                partnerEntity.setUid(country_code + "-" + party_id);
                                                partnerEntity.setCountry_code(country_code);
                                                partnerEntity.setParty_id(party_id);
                                                partnerEntity.setStatus("INIT");
                                                partnerEntity.setStatus_message("Registration Started !!");
                                                partnerEntity.setFrom_token(tokenCreated);
                                                partnerEntity.setRoles("{}");
                                                PartnerDTO partnerDTO = credentialsService.partnerDetails(partnerEntity);
                                                succesResponse = postDetails(endpoints.getUrl(), token, tokenCreated, partnerDTO.getPid());
                                            } /*else {
                                                try {
                                                    throw new Exception("No credential URL found!!");
                                                } catch (Exception e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }*/

                                        });
                            } else {
                                try {
                                    throw new Exception("Data empty!!");
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } else {
                            try {
                                throw new Exception("No matching version!!");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
            );
            return ResponseEntity.ok(succesResponse);
        } else {
            try {
                throw new Exception("Data empty!!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public VersionResponse getVersions(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<VersionResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<VersionResponse>() {
                });
        VersionResponse response = responseEntity.getBody();

        return response;


    }

    public VersionDetailsResponse getVersionsDetails(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<VersionDetailsResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<VersionDetailsResponse>() {
                });
        VersionDetailsResponse response = responseEntity.getBody();

        list = response.getData().getEndpoints();
        return response;
    }

    public String postDetails(String url, String token, String tokenCreated, Long pid) {

        CredentialRequest credentialRequestnew = new CredentialRequest();
        credentialRequestnew.setUrl("http://10.221.61.185:9898/emsp/api/versions/get-versions");
        credentialRequestnew.setCountry_code("KR");
        credentialRequestnew.setParty_id("LGE");
        credentialRequestnew.setToken(tokenCreated);

        com.lge.ocpi.emsp.model.dto.credentials.credentialRequest.BusinessDetails businessDetails = new com.lge.ocpi.emsp.model.dto.credentials.credentialRequest.BusinessDetails();
        Logo logo = new Logo();
        logo.setThumbnail("https://example.com/img/logo_thumb.jpg");
        logo.setWidth(Long.parseLong("512"));
        logo.setHeight(Long.parseLong("512"));
        logo.setCategory("OPERATOR");
        logo.setType("jpeg");
        logo.setUrl("https://example.com/img/logo.jpg");

        businessDetails.setLogo(logo);
        businessDetails.setName("ExampleOperator");
        businessDetails.setWebsite("http://example.com");
        credentialRequestnew.setBusiness_details(businessDetails);


        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + token);
        HttpEntity<CredentialRequest> requestEntity =
                new HttpEntity<>(credentialRequestnew, headers);
        CredentialResponseBody responseEntity = template.exchange(url, HttpMethod.POST, requestEntity, CredentialResponseBody.class).getBody();


        log.info(responseEntity.toString());


        PartnerDetails partnerDetails = new PartnerDetails();

        Emsp emsp = new Emsp();
        emsp.setUrl(responseEntity.getData().getUrl());

        BusinessDetails businessDetailsResponse = new BusinessDetails();
        businessDetailsResponse.setName(responseEntity.getData().getBusiness_details().getName());
        businessDetailsResponse.setWebsite(responseEntity.getData().getBusiness_details().getWebsite());

        emsp.setBusiness_details(businessDetailsResponse);
        emsp.setTo_token(responseEntity.getData().getToken());

        Endpoints endpoints = new Endpoints();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIdentifier()) {
                case "cdrs":
                    endpoints.setCdrs(list.get(i).getUrl());
                    break;
                case "credentials":
                    endpoints.setCredentials(list.get(i).getUrl());
                    break;
                case "locations":
                    endpoints.setLocations(list.get(i).getUrl());
                    break;
                case "commands":
                    endpoints.setCommands(list.get(i).getUrl());
                    break;
                case "sessions":
                    endpoints.setSessions(list.get(i).getUrl());
                    break;
                case "tariffs":
                    endpoints.setTariffs(list.get(i).getUrl());
                    break;
                case "tokens":
                    endpoints.setTokens(list.get(i).getUrl());
                    break;


            }

        }
        emsp.setEndpoints(endpoints);
        partnerDetails.setEmsp(emsp);

       /* PartnerEntity partnerEntity = new PartnerEntity();
        partnerEntity.setStatus("REGISTERED");
        partnerEntity.setStatus_message("Registration Successful");
        partnerEntity.setFrom_token(tokenCreated);
        partnerEntity.setRoles(partnerDetails.toString());*/



        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(partnerDetails);
            log.info("Roledata-->"+json);
            credentialsService.update(pid,json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }






        return responseEntity.toString();


    }
}
