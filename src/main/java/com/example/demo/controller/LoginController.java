package com.example.demo.controller;
/*
 * 作成者　粂井　ログイン管理のコントローラークラス
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;
@Controller
@RequestMapping("/login")
public class LoginController {
@GetMapping
public String showLogin(@ModelAttribute LoginForm form) {
// templatesフォルダ配下のlogin.htmlに遷移
return "login";
}
}
//15行目「@ModelAttribute LoginForm form」で、カスタムログイン画面の入力データを保持す
//るLoginFormオブジェクトをメソッドの引数として定義しています。この定義によりカスタム
//ログイン画面のフォームの入力フィールドとLoginFormクラスのプロパティが自動的に結びつ
//けられます。