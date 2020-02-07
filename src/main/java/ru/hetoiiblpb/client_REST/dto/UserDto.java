package ru.hetoiiblpb.client_REST.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class UserDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String roles;
}
