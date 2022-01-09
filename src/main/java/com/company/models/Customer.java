package com.company.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private double discount;
}
