package com.ducthang.ManagerUsers.dto;
import com.ducthang.ManagerUsers.ultil.Gender;
import com.ducthang.ManagerUsers.ultil.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Integer id;

    @NotNull(message = "username must be not null")
    private String username;

//    @NotNull(message = "password must be not null")
    private String password;

    @NotNull(message = "name must be not null")
    private String name;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private String dob;

    private Gender gender;

    private String phone;

    private String email;

    private String address;

    private Role role;

    private LocalDateTime createdAt;
}
