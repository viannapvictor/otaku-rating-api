package com.otakurating.user.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeycloakUserRepresentation {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private Boolean emailVerified;
    private List<String> roles;
    private Long createdTimestamp;
    private Long updatedTimestamp;
}