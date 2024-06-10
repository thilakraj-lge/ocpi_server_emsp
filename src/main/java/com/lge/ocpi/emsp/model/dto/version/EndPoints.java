package com.lge.ocpi.emsp.model.dto.version;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndPoints implements Serializable {

    @NotBlank(message = "Identifier should not be null or empty")
    private String identifier;
    @NotBlank(message = "Url should not be null or empty")
    private String url;
}
