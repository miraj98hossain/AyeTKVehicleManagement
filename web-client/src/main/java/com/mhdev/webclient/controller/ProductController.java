package com.mhdev.webclient.controller;

import com.mhdev.webclient.dto.requestdto.ProductReqDto;
import com.mhdev.webclient.dto.responsedto.ProductResDto;
import com.mhdev.webclient.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("product", new ProductResDto());
        return "product-page";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute ProductReqDto product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("products", productService.findAll());
        return "product-page";
    }
}

