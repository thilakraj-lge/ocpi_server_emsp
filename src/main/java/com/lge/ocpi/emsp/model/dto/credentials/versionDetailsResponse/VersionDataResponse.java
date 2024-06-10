package com.lge.ocpi.emsp.model.dto.credentials.versionDetailsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDataResponse implements Serializable {
    public Long id;
    private String version;
    private List<EndpointsListResponse> endpoints;
}