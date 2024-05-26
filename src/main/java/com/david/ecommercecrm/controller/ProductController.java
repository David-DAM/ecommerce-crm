package com.david.ecommercecrm.controller;

import com.david.ecommercecrm.domain.Product;
import com.david.ecommercecrm.exception.domain.InvalidPriceException;
import com.david.ecommercecrm.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ModelAndView modelAndView;

    @GetMapping
    public ModelAndView index(){

        List<Product> products = productRepository.findAll();
        modelAndView.addObject("products",products);
        modelAndView.setViewName("products/index");

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){

        modelAndView.setViewName("products/create");

        return modelAndView;
    }

    @PostMapping
    public ModelAndView save(@ModelAttribute Product product) throws InvalidPriceException {

        if (product.getPrice() > 200) throw new InvalidPriceException("The price is too hight");

        Product save = productRepository.save(product);

        boolean created = Objects.nonNull(save.getId());

        modelAndView.addObject("created", created);
        modelAndView.setStatus(HttpStatus.CREATED);
        modelAndView.setViewName("products/index");

        return modelAndView;
    }

}
