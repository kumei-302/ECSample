package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.form.CustomerForm;
import com.example.demo.service.CustomerService;

/*
 * 作成者　粂井　顧客情報の管理を行うコントローラークラス
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
        // バリデーションエラーがある場合は登録画面に戻る
        if (result.hasErrors()) {
            return "customer/register";
        }

        try {
            customerService.register(form);
            return "customer/complete";
        } catch (EmailAlreadyExistsException e) {
            // メールアドレス重複エラーの場合
            model.addAttribute("emailError", e.getMessage());
            return "customer/register";
        } catch (Exception e) {
            // その他のエラーの場合
            model.addAttribute("systemError", "システムエラーが発生しました。しばらくしてから再度お試しください。");
            return "customer/register";
        }
    }
}