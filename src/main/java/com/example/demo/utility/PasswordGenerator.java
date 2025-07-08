package com.example.demo.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
* ハッシュ化した文字列を返すクラス
*/
public class PasswordGenerator {
public static void main(String[] args) {
// 「BCrypt」のインスタンス化
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
// 入力値
String rawPassword = "adminpass";
// パスワードをハッシュ化
String encodedPassword = encoder.encode(rawPassword);
// 表示
System.out.println("ハッシュ化されたパスワード: " + encodedPassword);
}
}
//13行目で平文対象の文字列を設定し、15行目の「BCryptPasswordEncoder」の「encode」メソッ
//ドを使用してハッシュ化した文字列を生成し、17行目でハッシュ化した文字列を表示します。
//PasswordGeneratorファイルを選択し、右クリック→実行→Javaアプリケーションを行い（図
//A.11）、平文の「adminpass」をハッシュ化します（図A.12）。
//今回は認証情報のCRUD処理を作成することができませんが、認証テーブルへのパスワード登録
//時には、「平文」を「ハッシュ化」した「値」を認証用テーブルに「INSERT」してください。