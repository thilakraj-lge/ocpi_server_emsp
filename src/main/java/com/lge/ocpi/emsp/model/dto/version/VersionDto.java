package com.lge.ocpi.emsp.model.dto.version;


import com.lge.ocpi.emsp.model.enums.VersionNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersionDto {
    @NotNull
    private Long id;

    @NotBlank(message = "Version number should not be null or empty")
    public VersionNumber version;


    @NotBlank(message = "Url should not be null or empty")
    @Size(max = 255)
    public String url;
}
