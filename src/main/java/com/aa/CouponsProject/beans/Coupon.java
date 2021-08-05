package com.aa.CouponsProject.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.ORDINAL)
    private Categories categoryId;
    @ManyToOne
    private Company company;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;



}
