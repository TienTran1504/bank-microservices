package com.tdtien.accounts.service.client;

import com.tdtien.accounts.dto.CardsDto;
import com.tdtien.accounts.dto.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {
    @GetMapping(value="/api/v1/loans/fetch",consumes = "application/json")
    public ResponseEntity<LoansDto> getLoanDetails(@RequestParam String mobileNumber);

    
}
