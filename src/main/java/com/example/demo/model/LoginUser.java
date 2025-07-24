package com.example.demo.model;
/*作成者粂井
 * UserDetails実装クラス
 */
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
/**
* ユーザーの認証情報を表すUserDetails実装クラス
*/
public class LoginUser extends User {
	private String displayname;
/** 最低限の情報を保持したUserDetails
* 実装クラスUserを作成する */
public LoginUser(String username,
String password,
Collection<? extends GrantedAuthority> authorities) {
super(username, password, authorities);
}
}
//UserDetailsは、Spring Securityで使用されるインターフェースで、ユーザーの認証情報を表
//すものです。このインターフェースは、認証プロセスにおいてユーザーを識別し、その権限を管
//理するためにSpring Securityによって使用されます。開発者は、UserDetailsインターフェース
//を実装することで、アプリケーション固有のユーザー認証情報を定義できます。
//8行目「import org.springframework.security.core.userdetails.User;」は、Spring Securityが提
//供するUserDetailsインターフェースを簡易実装したクラスです。UserDetailsインターフェース
//を実装して認証用クラスを作成する場合、様々なメソッドをオーバーライドしなければいけませ
//ん。今回は必要最低限の機能を利用するため、Spring Securityが提供する「User」クラスという
//簡易版を利用しています。そのため12行目で「extends User」としています。
//18行目「super(username, password, authorities);」はUserクラスのコンストラクタです。