package com.example.demo.form;
/*
 * 作成者粂井ログイン時のバリデーションクラス
 */
import lombok.Data;
@Data
public class LoginForm {
/** ユーザー名 */
private String usernameInput;
/** パスワード */
private String passwordInput;
}