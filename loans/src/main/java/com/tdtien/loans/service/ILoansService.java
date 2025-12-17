package com.tdtien.loans.service;

import com.tdtien.loans.dto.LoansDto;

public interface ILoansService {

    /**
     * Create a new loan for a customer
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoan(String mobileNumber);

    /**
     * Get Loan Details by mobileNumber
     * @param mobileNumber - Mobile Number of the Customer
     *  @return Loan Details by mobileNumber
     */
    LoansDto getLoan(String mobileNumber);

    /**
     * Update loan details
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     * Delete loan details by mobileNumber
     * @param mobileNumber - Mobile Number of the Customer
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);

}