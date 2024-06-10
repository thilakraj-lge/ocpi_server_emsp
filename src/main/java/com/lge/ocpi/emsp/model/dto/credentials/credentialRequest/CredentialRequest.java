
package com.lge.ocpi.emsp.model.dto.credentials.credentialRequest;


import com.nimbusds.jose.shaded.gson.annotations.Expose;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialRequest {
    private BusinessDetails business_details;
    private String country_code;
    private String party_id;
    private String token;
    private String url;
}
