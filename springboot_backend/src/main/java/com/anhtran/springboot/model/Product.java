package com.anhtran.springboot.model;

import com.anhtran.springboot.enumType.MyBrand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    @Enumerated(EnumType.STRING)
    private MyBrand brand;

    private String model;

    private float price;

    private String description;

    private byte[] image;

    @ManyToOne(cascade = {CascadeType.DETACH , CascadeType.PERSIST
            , CascadeType.MERGE , CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {
            CascadeType.PERSIST , CascadeType.MERGE,
            CascadeType.DETACH , CascadeType.REFRESH
    })
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;




}
