package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AMapper;
import com.eazybytes.accounts.mapper.CMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.clients.CardsFeignClient;
import com.eazybytes.accounts.service.clients.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsClient;
    private final LoansFeignClient loansClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("account", "customerId", customer.getCustomerId().toString()));
        var cardsDto = cardsClient.fetchCardDetails(correlationId, mobileNumber);
        var loansDto = loansClient.fetchLoanDetails(correlationId, mobileNumber);
        var accountDto = AMapper.mapToAccountsDto(account, new AccountsDto());

        CustomerDetailsDto customerDetailsDto = CMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccounts(accountDto);
        if (null != cardsDto) {
            customerDetailsDto.setCards(cardsDto.getBody());
        }
        if (null != loansDto) {
            customerDetailsDto.setLoans(loansDto.getBody());
        }
        return customerDetailsDto;
    }
}
