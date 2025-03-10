package com.eazybytes.accounts.service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomeAlreadyExistException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AMapper;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private final AccountsMapper accountsMapper;
    private CustomerMapper customerMapper;

    @Override
    public void createAccount(CustomerDTO customerDto) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomeAlreadyExistException("Customer with same mobileNumber already exist.");
        }
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
//        customer.setCreatedBy("test");
//        customer.setCreatedAt(LocalDateTime.now());
        customer = customerRepository.save(customer);
        Accounts accounts = new Accounts(customer.getCustomerId(), null,
                AccountsConstants.SAVINGS, AccountsConstants.ADDRESS);
//        accounts.setCreatedBy("test");
//        accounts.setCreatedAt(LocalDateTime.now());
        accountRepository.save(accounts);
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("account", "customerId", customer.getCustomerId().toString()));
        var customerDto = CMapper.mapToCustomerDto(customer, new CustomerDTO());
        var accountDto = AMapper.mapToAccountsDto(account, new AccountsDto());
        customerDto.setAccountsDto(accountDto);
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        String mobileNumber = customerDTO.getMobileNumber();
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("account", "customerId", customer.getCustomerId().toString()));
        boolean isUpdated = false;
        if (customerDTO.getAccountsDto() != null) {
            var updateAccount = AMapper.mapToAccounts(customerDTO.getAccountsDto(), account);
            accountRepository.save(updateAccount);

            var updateCustomer = CMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(updateCustomer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
