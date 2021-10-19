package com.citybank.dto;

import java.util.List;

import com.citybank.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class LoginResponseDto {
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String token;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String name;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String username;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private List<String> roles;
}
