package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CustomerForm {
    @NotBlank(message = "氏名は必須です")
    private String customerName;

    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "正しいメールアドレス形式で入力してください")
    private String customerEmail;

    @NotBlank(message = "住所は必須です")
    private String customerAddress;

    // ゲッター・セッター（@Dataアノテーションで自動生成）
}