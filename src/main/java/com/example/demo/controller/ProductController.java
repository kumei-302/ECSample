package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/new")
//    public String showForm(Model model) {
//        model.addAttribute("product", new Product());
//        return "product_form";
//    }
//
//    @PostMapping("/save")
//    public String saveProduct(@ModelAttribute Product product, Model model) {
//        if (productService.existsByName(product.getProductName())) {
//            model.addAttribute("error", "この商品名は既に登録されています。");
//            return "product_form";
//        }
//
//        productService.save(product);
//        return "redirect:/products/list";
//    }
//
//    @GetMapping("/list")
//    public String list(Model model) {
//        model.addAttribute("products", productService.getAll());
//        return "product_list";
//    }
}