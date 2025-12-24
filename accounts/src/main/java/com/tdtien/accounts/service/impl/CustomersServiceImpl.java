package com.tdtien.accounts.service.impl;

import com.tdtien.accounts.dto.AccountsDto;
import com.tdtien.accounts.dto.CardsDto;
import com.tdtien.accounts.dto.CustomerDetailsDto;
import com.tdtien.accounts.dto.LoansDto;
import com.tdtien.accounts.entity.Accounts;
import com.tdtien.accounts.entity.Customer;
import com.tdtien.accounts.exception.ResourceNotFoundException;
import com.tdtien.accounts.mapper.AccountsMapper;
import com.tdtien.accounts.mapper.CustomerMapper;
import com.tdtien.accounts.repository.AccountsRepository;
import com.tdtien.accounts.repository.CustomerRepository;
import com.tdtien.accounts.service.ICustomersService;
import com.tdtien.accounts.service.client.CardsFeignClient;
import com.tdtien.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * Fetch Customer Details including Accounts, Loans and Cards
     *
     * @param mobileNumber
     * @return Customer Details by mobile number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto details = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        details.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.getLoanDetails(correlationId, mobileNumber);
        details.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.getCardDetails(correlationId, mobileNumber);
        details.setCardsDto(cardsDtoResponseEntity.getBody());

        return details;
    }
}
