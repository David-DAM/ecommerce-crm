package com.david.ecommercecrm.controller;

import com.david.ecommercecrm.domain.Product;
import com.david.ecommercecrm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String findAll(Model model){

        List<Product> products = productRepository.findAll();
        model.addAttribute("products",products);

        return "products/index";
    }

    @PostMapping
    public String create(Product product){
        Product save = productRepository.save(product);

        String created = (Objects.nonNull(save.getId())) ? "created=true" : "created=false";

        return "products/index.html?"+created;
    }

}
