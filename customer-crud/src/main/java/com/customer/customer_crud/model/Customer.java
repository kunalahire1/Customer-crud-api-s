package com.customer.customer_crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String street;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String email;
    @Id
    private String phone;
}
