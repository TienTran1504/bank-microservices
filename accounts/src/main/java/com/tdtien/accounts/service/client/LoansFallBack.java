package com.tdtien.accounts.service.client;

import com.tdtien.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements  LoansFeignClient{
    /**
     * @param correlationId
     * @param mobileNumber
     * @return
     */
    @Override
    public ResponseEntity<LoansDto> getLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
