package com.anhtran.springboot.model;

import com.anhtran.springboot.enumType.MypromotionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "promotion")
public class Promotion {

    private Long promotionId;
    private String promotionName;
    private MypromotionType promotionType;
    private Date beginDate;
    private Date endDate;
    private float value;
    private List<Product> products;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    @Column(name = "promotion_name")
    public String getPromotionName() {
        return promotionName;
    }

    @Column(name = "value")
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    @Column(name = "promotion_type")
    @Enumerated(EnumType.STRING)
    public MypromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(MypromotionType promotionType) {
        this.promotionType = promotionType;
    }

    @Column(name = "begin_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getBeginDate() {
        return beginDate;
    }


    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "category" , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.PERSIST
            , CascadeType.REFRESH})
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }




}
