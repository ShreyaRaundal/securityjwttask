package com.msf.securityjwttask.services;

import com.msf.securityjwttask.entity.Product;
import com.msf.securityjwttask.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private static final Logger log= LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        log.info("Adding product: {}", product.getName());
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());

        log.info("Updating product id {}", id);
        return productRepository.save(product);
    }


    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Deleted product id {}", id);
        return "Product deleted successfully";
    }


    public String buyProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() <= 0) {
            throw new RuntimeException("Product out of stock");
        }

        product.setQuantity(product.getQuantity() - 1);
        productRepository.save(product);

        log.info("Product purchased: {}", product.getName());
        return "Product purchased successfully";
    }
}

