package com.aa.CouponsProject.beans;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Categories categoryId;
    @ManyToOne
    @ToString.Exclude
    private Company company;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;



}
