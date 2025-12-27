package com.tdtien.message.dto;

/**
 * Accounts Message DTO
 *
 * @param accountNumber  Account Number
 * @param name           Name
 * @param email          Email
 * @param mobileNumber   Mobile Number
 */
public record AccountsMsgDto(Long accountNumber, String name, String email, String mobileNumber) {
}
