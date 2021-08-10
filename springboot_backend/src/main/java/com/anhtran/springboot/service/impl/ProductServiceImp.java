package com.anhtran.springboot.service.impl;

import com.anhtran.springboot.constant.ProductPaging;
import com.anhtran.springboot.model.Category;
import com.anhtran.springboot.model.Product;
import com.anhtran.springboot.model.Promotion;
import com.anhtran.springboot.repository.CategoryRepository;
import com.anhtran.springboot.repository.ProductRepository;
import com.anhtran.springboot.repository.PromotionRepository;
import com.anhtran.springboot.repository.criteria.ProductCriteriaRepository;
import com.anhtran.springboot.repository.criteria.ProductCriteriaSearch;
import com.anhtran.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ProductCriteriaRepository productCriteriaRepository;


    @Override
    public List<Product> listProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> listAll(Long categoryId , Long promotionId) {
        Category maybeCategory = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> {
                    throw  new RuntimeException("this category is not found");
                });
        Promotion maybePromotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this promotion is not found");
                });
        return productRepository.findAllByCategoryAndPromotion(maybeCategory, maybePromotion);
    }

    @Override
    public Page<Product> findAll(ProductPaging productPaging , ProductCriteriaSearch productCriteriaSearch) {
        return productCriteriaRepository.findAllWithFilters(productPaging , productCriteriaSearch);
    }

    @Override
    public Product save(Long categoryId , Long promotionId , Product product , MultipartFile multipartFile) throws IOException {
        Category maybeCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                   throw new RuntimeException("this category is not found");
                });
        Promotion maybePromotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> {
                   throw new RuntimeException("this product is not found");
                });
        productRepository.findByProductName(product.getProductName()).ifPresent(
                (p) -> {
                    throw new RuntimeException("this product with product name " +
                            p.getProductName() + " is already exist");
                }
        );
        product.setCategory(maybeCategory);
        product.setPromotion(maybePromotion);
        product.setImage(multipartFile.getBytes());
        return productRepository.save(product);
    }

    @Override
    public Product get(Long productId , Long categoryId , Long promotionId) {
        return productRepository.findByProductIdAndCategoryCategoryIdAndPromotionPromotionId(productId , categoryId , promotionId)
                .orElseThrow(() -> {
                    throw new RuntimeException("this product is not found");
                });
    }


    @Override
    public Product update(Long categoryId , Long promotionId , Long productId , Product product, MultipartFile file) throws IOException {
        Product maybeProduct = productRepository.findByProductIdAndCategoryCategoryIdAndPromotionPromotionId(productId , categoryId , promotionId)
                .orElseThrow(() -> {
                   throw new RuntimeException("this product with id " + productId
                           + " " + categoryId + " " + promotionId + " is not found");
                });
        Optional<Product> checkProductName = productRepository.findByProductName(product.getProductName());
        Optional<Product> checkAlsoById = productRepository.findById(productId);
        if(checkProductName.isPresent() && checkProductName.get().getProductId() != checkAlsoById.get().getProductId()) {
            throw new RuntimeException("this product is already exist");
        }
        maybeProduct.setProductName(product.getProductName());
        maybeProduct.setBrand(product.getBrand());
        maybeProduct.setModel(product.getModel());
        maybeProduct.setPrice(product.getPrice());
        maybeProduct.setDescription(product.getDescription());
        maybeProduct.setImage(file.getBytes());
        maybeProduct.setCategory(product.getCategory());
        maybeProduct.setPromotion(product.getPromotion());
        return productRepository.save(maybeProduct);
    }

    @Override
    public void delete(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> {
                   throw new RuntimeException("this product with this category is not found");
                });
        productRepository.deleteById(productId);
    }

    @Override
    public byte[] compressFile(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        if(!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer , 0 , count);
        }
        try {
            outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompressFile(byte[] data) throws DataFormatException, IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        inflater.finished();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        if(!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer , 0 , count);
        }
        try {
            outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Decompressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
}
