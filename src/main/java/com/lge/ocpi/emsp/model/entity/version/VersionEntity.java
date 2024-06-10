package com.lge.ocpi.emsp.model.entity.version;


import com.lge.ocpi.emsp.model.enums.VersionNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "version")
public class VersionEntity {

    @NotNull
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    /**
     * The version number
     */
    @NotNull
    @Column(name = "versionNumber")
    public VersionNumber version;

    /**
     * URL to the endpoint containing version specific information
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "url")
    public String url;
}
