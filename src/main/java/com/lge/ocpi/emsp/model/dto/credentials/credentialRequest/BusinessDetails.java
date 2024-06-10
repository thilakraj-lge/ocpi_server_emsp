
package com.lge.ocpi.emsp.model.dto.credentials.credentialRequest;


import com.nimbusds.jose.shaded.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDetails {
    private Logo logo;
    private String name;
    private String website;
}
