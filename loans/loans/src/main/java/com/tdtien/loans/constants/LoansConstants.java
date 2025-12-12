package com.tdtien.loans.constants;

public class LoansConstants {
    private LoansConstants() {
        // private constructor to prevent instantiation
    }

    public static final String  HOME_LOAN = "Home Loan";
    public static final int  NEW_LOAN_LIMIT = 1_00_000;
    public static final String  STATUS_201 = "201";
    public static final String  MSG_201 = "Loan created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MSG_200 = "Request processed successfully";
    public static final String STATUS_400 = "400";
    public static final String MSG_400 = "Bad request. Please check the provided data.";
    public static final String STATUS_500 = "500";
    public static final String MSG_500 = "Internal server error occurred. Please try again later or contact support.";
}
