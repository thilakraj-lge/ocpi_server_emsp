
package com.lge.ocpi.emsp.model.dto.credentials.credentialRequest;


import com.nimbusds.jose.shaded.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logo {
    private String category;
    private Long height;
    private String thumbnail;
    private String type;
    private String url;
    private Long width;
}
