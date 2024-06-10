package com.lge.ocpi.emsp.model.dto.version;


import com.lge.ocpi.emsp.model.enums.VersionNumber;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDetailsDTO implements Serializable {

    @NotNull
    @SerializedName("id")
    private Long id;

    @NotBlank(message = "Version number should not be null or empty")
    @SerializedName("version")
    private VersionNumber version;

    @NotEmpty(message = "Endpoints should not be empty, should contain atleast one object")
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    @SerializedName("endpoints")
    private List<Map<String, Object>> endpoints = new ArrayList<Map<String, Object>>();
}
