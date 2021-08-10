package com.anhtran.springboot.service;

import com.anhtran.springboot.constant.PromotionPaging;
import com.anhtran.springboot.model.Promotion;
import com.anhtran.springboot.repository.criteria.PromotionCriteriaSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PromotionService {

    public Page<Promotion> findAll(PromotionPaging promotionPaging , PromotionCriteriaSearch promotionCriteriaSearch);

    public List<Promotion> listAll();

    public Promotion save(Promotion promotion);

    public Promotion get(Long promotionId);

    public Promotion update(Promotion promotion , Long promotionId);

    public void delete(Long promotionId);

}
