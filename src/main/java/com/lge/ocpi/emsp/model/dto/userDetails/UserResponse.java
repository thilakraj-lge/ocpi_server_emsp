package com.lge.ocpi.emsp.model.dto.userDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lge.ocpi.emsp.model.entity.autentication.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {

    private Long id;
    private String username;

    private String email;

    private String name;

    private String phoneNumber;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Set<UserRole> roles;
}
