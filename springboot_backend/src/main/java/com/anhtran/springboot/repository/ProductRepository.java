package com.anhtran.springboot.repository;

import com.anhtran.springboot.model.Category;
import com.anhtran.springboot.model.Product;
import com.anhtran.springboot.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

    List<Product> findAllByCategoryAndPromotion(Category category , Promotion promotion);

    Optional<Product> findByProductIdAndCategoryCategoryIdAndPromotionPromotionId(Long id , Long categoryId , Long promotionId);


}
