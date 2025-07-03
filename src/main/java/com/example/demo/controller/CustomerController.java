package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.CustomerForm;
import com.example.demo.service.CustomerService;

/*作成者　粂井
*ユーザー情報をコントロールするクラス
*/
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/register")
    public String showForm(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/register";
    }

    @PostMapping("/customer/register")
    public String register(@ModelAttribute @Valid CustomerForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "customer/register";
        }

        customerService.register(form);
        return "customer/complete";
    }
}