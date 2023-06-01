package com.datacosmos.datacosmosproject.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The UserDto class represents the data transfer object for User entities.
 * It is used for transferring user data between the client and server layers of the application.
 * It is annotated with Lombok annotations such as @Getter, @Setter, @NoArgsConstructor,
 * and @AllArgsConstructor to generate the necessary constructor, getter, and setter methods.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @Column(nullable=false, unique=true, name = "username")
    private String username;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;


}