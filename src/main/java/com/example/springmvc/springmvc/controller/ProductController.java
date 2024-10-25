package com.example.springmvc.springmvc.controller;


import com.example.springmvc.springmvc.model.Product;
import com.example.springmvc.springmvc.repository.ProductRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products")
@Api(value = "ProductsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @RequestMapping(path = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(path = "/products/add" , method = RequestMethod.GET)
    public String createProduct(Model model){
        model.addAttribute("product", new Product());
        return "edit";
    }

    @RequestMapping(path = "products" , method = RequestMethod.POST)
    public String saveProduct(Product product){
        productRepository.save(product);
        return "redirect:/";
    }

    @RequestMapping(path = "/products" , method = RequestMethod.GET)
    public String getAllProducts(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(path = "/products/edit/{id}" , method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
        } else {
            return "errorPage";
        }

        return "edit";
    }

    @RequestMapping(path = "/products/delete/{id}" , method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(value = "id") String id){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
        } else {
            return "errorPage";
        }
        return "redirect:/products";
    }
}
