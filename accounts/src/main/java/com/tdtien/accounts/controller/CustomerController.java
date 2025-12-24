package com.tdtien.accounts.controller;

import com.tdtien.accounts.dto.CustomerDetailsDto;
import com.tdtien.accounts.dto.ErrorResponseDto;
import com.tdtien.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Customer Service",
        description = "CRUD REST APIs - Create, Read, Update, Delete Customer details"
)
@RestController
@RequestMapping(path="/api/v1/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomersService customerService;

    public CustomerController(ICustomersService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Get Customer Details REST API",
            description = "REST API to get customer details by mobile number"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode ="200",
                    description ="Customer fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch-customer-details")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("bank-correlation-id") String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                   String mobileNumber) {
        logger.debug("Fetching Customer Details REST API for correlation id: {}", correlationId);
        CustomerDetailsDto customerDetails = customerService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetails);
    }
}
