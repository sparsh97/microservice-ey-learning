package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsDto {

    @NotEmpty(message = "Mobile number can not be empty")
    private String mobileNumber;

    @NotEmpty(message = "Card Number can not be empty")
    private String cardNumber;

    @NotEmpty(message = "Card Type can not be empty")
    private String cardType;


    private Double totalLimit;

    private Double amountUsed;

    private Double availableAmount;
}