package com.tdtien.accounts.service.impl;

import com.tdtien.accounts.constants.AccountsConstants;
import com.tdtien.accounts.dto.AccountsDto;
import com.tdtien.accounts.dto.CustomerDto;
import com.tdtien.accounts.entity.Accounts;
import com.tdtien.accounts.entity.Customer;
import com.tdtien.accounts.exception.CustomerAlreadyExistsException;
import com.tdtien.accounts.exception.ResourceNotFoundException;
import com.tdtien.accounts.mapper.AccountsMapper;
import com.tdtien.accounts.mapper.CustomerMapper;
import com.tdtien.accounts.repository.AccountsRepository;
import com.tdtien.accounts.repository.CustomerRepository;
import com.tdtien.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * Create a new account for a customer.
     *
     * @param customerDto the customer data transfer object containing customer details
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number: " + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccountForCustomer(savedCustomer));
    }

    /**
     * Get account details by mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     */
    @Override
    public CustomerDto getAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
                );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        return customerDto;
    }

    /**
     * @param customerDto Update account details.
     * @return boolean indicating if the update was successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
                    );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
                    );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * Delete account by mobile number.
     *
     * @param mobileNumber
     * @return boolean indicating if the deletion was successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccountForCustomer(Customer savedCustomer) {
        Accounts account = new Accounts();
        account.setCustomerId(savedCustomer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        return account;
    }
}
