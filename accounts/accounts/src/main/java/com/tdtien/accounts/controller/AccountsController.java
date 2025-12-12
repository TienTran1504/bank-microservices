package com.tdtien.accounts.controller;

import com.tdtien.accounts.constants.AccountsConstants;
import com.tdtien.accounts.dto.CustomerDto;
import com.tdtien.accounts.dto.ErrorResponseDto;
import com.tdtien.accounts.dto.ResponseDto;
import com.tdtien.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts Service",
        description = "CRUD REST APIs - Create, Read, Update, Delete account details"
)
@RestController
@RequestMapping(path="/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create account details for a customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode ="201",
                    description ="Account created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(path="/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MSG_201));
    }

    @Operation(
            summary = "Get Account REST API",
            description = "REST API to get account details by mobile number"

    )
    @ApiResponses({
            @ApiResponse(
                responseCode ="200",
                description ="Account fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam
                                                             @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                             String mobileNumber) {
        CustomerDto customerDto = iAccountsService.getAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update account details for a customer"
    )
    @ApiResponses({
           @ApiResponse(
                     responseCode = "200",
                     description = "Account updated successfully"
           ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        return isUpdated ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MSG_200))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(AccountsConstants.STATUS_400, AccountsConstants.MSG_400));
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete account details by mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        return isDeleted ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MSG_200))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(AccountsConstants.STATUS_400, AccountsConstants.MSG_400));
    }
}
