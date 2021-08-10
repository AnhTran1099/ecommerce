package com.anhtran.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category" , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.PERSIST
            , CascadeType.REFRESH})
    private List<Product> products;


}
