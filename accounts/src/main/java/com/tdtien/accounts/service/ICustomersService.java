package com.tdtien.accounts.service;

import com.tdtien.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    /**
     * Fetch Customer Details including Accounts, Loans and Cards
     * @param mobileNumber
     * @return Customer Details by mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
