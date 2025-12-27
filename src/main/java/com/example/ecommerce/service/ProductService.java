package com.example.ecommerce.service;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @NonNull
    public Product getProductById(@NonNull Long id) {
        return Objects.requireNonNull(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id)));
    }

    @NonNull
    public Product createProduct(@NonNull Product product) {
        return productRepository.save(product);
    }

    @NonNull
    public Product updateProduct(@NonNull Long id, @NonNull Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(@NonNull Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public Page<Product> getProductsByPriceRange(double min, double max, int page, int size) {
        if (min > max) {
            throw new IllegalArgumentException("الحد الأدنى للسعر لا يمكن أن يكون أكبر من الحد الأعلى");
        }
        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findByPriceBetween(min, max, pageable);
    }
}
