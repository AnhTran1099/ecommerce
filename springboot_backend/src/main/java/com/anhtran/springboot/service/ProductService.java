package com.anhtran.springboot.service;


import com.anhtran.springboot.constant.ProductPaging;
import com.anhtran.springboot.model.Product;
import com.anhtran.springboot.repository.criteria.ProductCriteriaSearch;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

public interface ProductService {

    public List<Product> listProducts();

    public List<Product> listAll(Long categoryId , Long PromotionId);

    public Page<Product> findAll(ProductPaging productPaging , ProductCriteriaSearch productCriteriaSearch);

    public Product save(Long categoryId , Long productId , Product product , MultipartFile multipartFile) throws IOException;

    public Product get(Long productId , Long categoryId , Long promotionId);

    public Product update(Long categoryId , Long promotionId , Long productId , Product product , MultipartFile multipartFile) throws IOException;

    public void delete(Long productId);

    public byte[] compressFile(byte[] data) throws IOException;

    public byte[] decompressFile(byte[] data) throws DataFormatException, IOException;
}
