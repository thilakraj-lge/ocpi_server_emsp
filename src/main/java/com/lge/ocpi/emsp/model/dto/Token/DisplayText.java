package com.lge.ocpi.emsp.model.dto.Token;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisplayText {

    /**
     * Language Code ISO 639-1.
     */
    @NotEmpty
    @NotBlank(message = "Language cannot be null or empty")
    @Size(min = 2, max = 2)
    private String language;
    /**
     * Text to be displayed to end user. No markup, html etc. allowed.
     */
    @NotEmpty
    @NotBlank(message = "Text cannot be null or empty")
    @Size(min = 1, max = 512)
    private String text;
}
