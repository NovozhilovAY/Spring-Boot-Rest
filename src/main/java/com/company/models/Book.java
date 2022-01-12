package com.company.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private String storage;

    @Column(nullable = false)
    private int qty;
}
