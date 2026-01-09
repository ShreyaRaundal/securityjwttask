package com.msf.securityjwttask.controller;

import com.msf.securityjwttask.annotation.MFS;
import com.msf.securityjwttask.entity.Product;
import com.msf.securityjwttask.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//http://localhost:8005/product/add
    @MFS
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

//    http://localhost:8005/product/all

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

// http://localhost:8005/product/update/1
    @MFS
    @PutMapping("/update/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        return productService.updateProduct(id, product);
    }

//   http://localhost:8005/product/delete/5
    @MFS
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

//   http://localhost:8005/product/buy/2
    @MFS
    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id) {
        return productService.buyProduct(id);
    }

//    http://localhost:8005/product/search
    @GetMapping("/search")
    public String searchProduct() {
        log.info("Public product search");
        return "Product search executed (no token required)";
    }
}

