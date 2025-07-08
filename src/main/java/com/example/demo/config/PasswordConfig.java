package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class PasswordConfig {
@Bean
public PasswordEncoder passwordEncoder() {
// エンコードの設定
	return new BCryptPasswordEncoder();
}
}
//8行目で「@Configuration」アノテーションを使用することにより、このクラスがSpringの設
//定クラスであることを示しています。このアノテーションによって、Springはこのクラス内の
//メソッドからBean（Springによって管理されるオブジェクト）を生成し、それをアプリケーショ
//ンのコンテナに登録します。
//　10行目の「@Bean」アノテーションは、メソッドがBeanを生成することを示します。Spring
//はこのメソッドが返すオブジェクトを依存性注入（DI）の対象として扱います。
//　13行目「return new BCryptPasswordEncoder();」は、Spring Securityにおいてパスワードを安
//全にハッシュ化するために使用されるクラスの一つです。BCryptという特定のハッシュ関数を
//用いて、パスワードをハッシュ化します。この設定により、ハッシュ化されたパスワードを認証
//できるようになります。