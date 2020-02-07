package ru.hetoiiblpb.client_REST.dto;

import lombok.Data;

@Data
public class AuthorizationResponseDto {
    private String username;
    private String token;
    private String roles;
}
