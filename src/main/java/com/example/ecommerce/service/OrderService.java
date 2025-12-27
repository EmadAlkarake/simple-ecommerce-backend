package com.example.ecommerce.service;

import com.example.ecommerce.exception.InsufficientStockException;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order placeOrder(Order order) {
        // 1. Validate if product exists
        Product product = productRepository.findById(order.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + order.getProductId()));

        // 2. Check if there is enough stock
        if (product.getStockQuantity() < order.getQuantity()) {
            throw new InsufficientStockException("Not enough stock for product: " + product.getName());
        }

        // 3. Decrease stock
        product.setStockQuantity(product.getStockQuantity() - order.getQuantity());
        productRepository.save(product);

        // 4. Calculate total amount
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(order.getQuantity())));
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }
}
