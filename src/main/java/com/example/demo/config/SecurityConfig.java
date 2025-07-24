package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
/*
 * 作成者粂井　セキュリティの権限の情報を管理するクラス
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	/** DI対象が存在すれば、DIして使用する */
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	// SecurityFilterChainのBean定義
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http
	// ★HTTPリクエストに対するセキュリティ設定
	.authorizeHttpRequests(authz -> authz
	// 【パブリック】ログイン不要でアクセス可能
	.requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
	// 【商品閲覧】ログイン不要でアクセス可能
	.requestMatchers("/", "/products", "/products/**", "/search", "/categories/**").permitAll()
	// 【API - 商品取得】ログイン不要でアクセス可能
	.requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
			
	// 【ユーザー機能】ログイン必須
	.requestMatchers("/favorites/**", "/cart/**", "/orders/**", "/profile/**").authenticated()
	// 【API - ユーザー機能】ログイン必須
	.requestMatchers("/api/favorites/**", "/api/cart/**", "/api/orders/**").authenticated()
			
	// 【管理者権限設定】url:/adminに入ってるページは管理者しかアクセスできない
	.requestMatchers("/admin/**").hasAuthority("ADMIN")
	// 【削除権限設定】DELETEメソッドでの削除操作は管理者権限が必要
	.requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
	// 【削除権限設定】削除関連のURLパターン - 管理者が必要
	.requestMatchers("/*/delete").hasAuthority("ADMIN")
	.requestMatchers("/*/delete/*").hasAuthority("ADMIN")
	// 【削除権限設定】特定のエンティティの削除は管理者権限が必要
	.requestMatchers("/users/*/delete").hasAuthority("ADMIN")
	.requestMatchers("/products/*/delete").hasAuthority("ADMIN")
	// その他のリクエストは認証が必要
	.anyRequest().authenticated())
	// ★フォームベースのログイン設定
	.formLogin(form -> form
	// カスタムログインページのURLを指定
	.loginPage("/login")
	// ログイン処理のURLを指定
	.loginProcessingUrl("/authentication")
	// ユーザー名のname属性を指定
	.usernameParameter("usernameInput")
	// パスワードのname属性を指定
	.passwordParameter("passwordInput")
	// ログイン成功時のリダイレクト先を指定
	.defaultSuccessUrl("/")
	// ログイン失敗時のリダイレクト先を指定
	.failureUrl("/login?error"))
	// ★ログアウト設定
	.logout(logout -> logout
	// ログアウトを処理するURLを指定
	.logoutUrl("/logout")
	// ログアウト成功時のリダイレクト先を指定
	.logoutSuccessUrl("/login?logout")
	// ログアウト時にセッションを無効にする
	.invalidateHttpSession(true)
	// ログアウト時にCookieを削除する
	.deleteCookies("JSESSIONID")
	);
	return http.build();
	}
}
//　このソースコードは、Spring Securityを使用してWebアプリケーションのセキュリティ設定
//をカスタマイズするための設定クラスです。このクラスで、どのURLにアクセスするために認
//証が必要か、ログイン処理の扱い方など、セキュリティ関連のカスタマイズを定義しています。
//　9行目の「@Configuration」は、このクラスがSpringの設定クラスであることを示します。10
//行目の「@EnableWebSecurity」は、Spring Securityを有効化し、Webセキュリティの設定をカ
//スタマイズすることを意味します。つまり、この2つのアノテーションをクラスに付けることで、
//このクラスがセキュリティのカスタマイズ設定を担当することを表します。
//　21行目～36行目では、SecurityFilterChainのBeanを定義することで、HTTPリクエストに対
//するセキュリティ設定を行います。SecurityFilterChainは、Webリクエストがサーバーに到達す
//る際に一連のセキュリティチェックを実施します。
//　24行目～29行目は、HTTPリクエストに対するセキュリティ設定です。
//　26行目「.authorizeHttpRequests(authz -> authz」は、どのHTTPリクエストに認証が必要かを
//定義しています。authz -> authzの部分は、ラムダ式です。ラムダ式（Lambda Expression）は、
//Java 8で導入された機能で、簡潔にコードを記述するための方法です。
//　28行目「.requestMatchers("/login").permitAll()」は、「/login」へのアクセスは認証を必要とし
//ないことを指定しています。つまり、ログインページは誰でもアクセス可能です。
//　30行目「.anyRequest().authenticated()」は、その他の全てのリクエストには認証が必要である
//ことを指定しています。
//　31行目～35行目では、フォームベースのログイン設定をしており、「.formLogin(form ->
//form.loginPage("/login"))」でカスタムログインページのURLを設定しています。28行目の「return
//http.build();」は、セキュリティ設定を構築し、最終的なSecurityFilterChainオブジェクトを返す
//ために使用されます。
//ログイン処理のURLを指定
//36行目「.loginProcessingUrl("/authentication")」は、ユーザーがログインフォームを送信する
//際に使用するURLを指定しています。つまり認証処理を実行するURLを指定しています。何も
//設定しない場合、Spring Securityはデフォルトで「/login」をログイン処理のURLとして使用し
//ますが、ここではカスタム設定としてURL「/authentication」に変更しています。
//ユーザー名とパスワードのname属性を指定
//38行目「.usernameParameter("usernameInput")」は、ログインフォーム内のユーザー名入力
//フィールドのname属性を指定します。何も設定しない場合、Spring Securityはデフォルトで
//「username」をname属性に使用しますが、ここでは「usernameInput」に変更しています。
//40行目「.passwordParameter("passwordInput")」は、 ログインフォーム内のパスワード入力
//フィールドのname属性を指定します。何も設定しない場合、Spring Securityはデフォルトで
//「password」をname属性に使用しますが、ここでは「passwordInput」に変更しています。
//ログイン成功時と失敗時のリダイレクト先を指定
//42行目「.defaultSuccessUrl("/")」は、ログイン成功時のリダイレクト先を指定します。デフォ
//ルトでは、Spring Securityはログイン前にユーザーがアクセスしようとしていたページにリダ
//イレクトしますが、ここではメニュー画面を表示するURL[「/」を設定しています。
//44行目「.failureUrl("/login?error")」は、ログイン失敗時のリダイレクト先を指定します。デフォ
//ルトでは、ログイン失敗時にログインページにリダイレクトされますが、ここではエラーパラメー
//タ「error」を追加することで、ログインに失敗したことをユーザーに知らせるメッセージをビュー
//側で表示できます。
//ログアウトURLの指定
//48行目「.logoutUrl("/logout")」は、ログアウト処理を行うためのURLをカスタマイズしていま
//す。デフォルトでは、Spring SecurityはURL「/logout」をログアウト処理のURLとして使用しま
//すが、ここでは、わかりやすいように設定を明示的に指定しています。
//ログアウト成功時のリダイレクト先
//50行目「.logoutSuccessUrl("/login?logout")」は、ユーザーがログアウトに成功した後にリダ
//イレクトされるURLを指定しています。デフォルトでは、Spring Securityはログインページに
//リダイレクトしますが、ここではクエリパラメータlogoutを追加することで、ログアウトした
//ことをユーザーに知らせるメッセージをビュー側で表示できます。
//セッションの無効化
//52行目「.invalidateHttpSession(true)」は、ログアウト時にHTTPセッションを無効にする設定
//です。これはセキュリティのベストプラクティスであり、セッションハイジャック（注2）を防ぐの
//に役立ちます。デフォルト設定では、この挙動は有効になっていますが、わかりやすいように記
//述しています。
//クッキーの削除
//54行目「.deleteCookies("JSESSIONID")」は、ログアウト時に特定のクッキー（ ここでは
//JSESSIONID）を削除する設定です。JSESSIONID（注3）はセッション識別用のクッキーで、ログアウ
//ト時にこれを削除することで、古いセッションが再利用されるのを防ぎます。