package ru.hetoiiblpb.client_REST.dto;


import lombok.Data;

@Data
public class AuthorizationRequestDto {
    private String username;
    private String password;
}
