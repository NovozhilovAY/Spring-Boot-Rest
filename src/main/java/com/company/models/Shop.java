package com.company.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shops")
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private double commission;
}
