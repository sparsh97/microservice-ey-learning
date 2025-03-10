package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     * Create an account for customer
     * @param customerDTO - CustomerDto Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Return account details by mobile number
     * @param mobileNumber
     * @return
     */
    CustomerDTO fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
}
