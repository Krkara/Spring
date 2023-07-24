package com.kristjan.webshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "seq", initialValue = 4031421, allocationSize = 1)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private Date creationDate;
    private String paymentState;
    private double totalSum;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderRow> orderRow;
    @ManyToOne
    private Person person;

}
