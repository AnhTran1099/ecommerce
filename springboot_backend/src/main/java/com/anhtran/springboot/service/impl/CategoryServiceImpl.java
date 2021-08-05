package com.anhtran.springboot.service.impl;

import com.anhtran.springboot.constant.CategoryPage;
import com.anhtran.springboot.model.Category;
import com.anhtran.springboot.repository.CategoryRepository;
import com.anhtran.springboot.repository.criteria.CategoryCriteriaRepository;
import com.anhtran.springboot.repository.criteria.CategoryCriteriaSearch;
import com.anhtran.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryCriteriaRepository categoryCriteriaRepository;

    public CategoryServiceImpl(){

    }

    @Override
    public Page<Category> findAll(CategoryPage categoryPage, CategoryCriteriaSearch categoryCriteriaSearch) {
        return categoryCriteriaRepository.findAllWithPagingAndFiltering(categoryPage, categoryCriteriaSearch);
    }

    @Override
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

//    @Override
//    public Category findById(Long categoryId) {
//        Category maybeCategory = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> {
//                    throw new RuntimeException("")
//                })
//        return null;
//    }

    @Override
    public Category update(Category category, Long categoryId) {
        Category maybeCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    throw new RuntimeException("the category is not found");
                });
        Optional<Category> checkCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        Optional<Category> checkCategoryId = categoryRepository.findById(categoryId);
        if(checkCategoryName.isPresent() &&
                checkCategoryName.get().getCategoryId() != checkCategoryId.get().getCategoryId()) {
            throw new RuntimeException("the category is already exsist");
        }
        maybeCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(maybeCategory);
    }

    @Override
    public Category create(Category category) {
        categoryRepository.findByCategoryName(category.getCategoryName()).ifPresent(
                (c) -> {
                    throw new RuntimeException("this category with this category name: "
                            + c.getCategoryName() + "is already exsist");
                }
        );
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(()-> {
                    throw new RuntimeException("this category is not found");
                });

        categoryRepository.deleteById(categoryId);

    }

}
