package com.tdtien.accounts.service;

import com.tdtien.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create a new account for a customer.
     *
     * @param customerDto the customer data transfer object containing customer details
     */
    void createAccount(CustomerDto customerDto);


    /**     * Get account details by mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     */
     CustomerDto getAccount(String mobileNumber);

    /**
     * Update account details.
     * @param customerDto
     * @return boolean indicating if the update was successful or not
     */
     boolean updateAccount(CustomerDto customerDto);

    /**
     * Delete account by mobile number.
     * @param mobileNumber
     * @return boolean indicating if the deletion was successful or not
     */
     boolean deleteAccount(String mobileNumber);

}
