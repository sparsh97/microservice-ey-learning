package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerDetailsDto {
    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Mobile number can not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;
    private AccountsDto accounts;
    private CardsDto cards;
    private LoansDto loans;
}
