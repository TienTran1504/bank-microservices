package com.tdtien.accounts.entity;

import jakarta.persistence.*;

import lombok.*;


@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
//@Table(name = "customers") dont need to specify table name if it matches class name
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="name")
    public String name;

    @Column(name="email")
    public String email;

    @Column(name="mobile_number")
    public String mobileNumber;
}
