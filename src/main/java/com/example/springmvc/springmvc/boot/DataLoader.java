package com.example.springmvc.springmvc.boot;

import com.example.springmvc.springmvc.model.Product;
import com.example.springmvc.springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product product = new Product();

        Product product1 = new Product();
        product1.setName("T-Shirt");
        product1.setType("Clothing");
        product1.setCategory("Men's");
        product1.setDescription("A comfortable and stylish T-shirt.");
        product1.setPrice(19.99);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Laptop");
        product2.setType("Electronics");
        product2.setCategory("Computers");
        product2.setDescription("A high-performance laptop for work and gaming.");
        product2.setPrice(999.99);
        productRepository.save(product2);
    }
}
