package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Mobile number can not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
