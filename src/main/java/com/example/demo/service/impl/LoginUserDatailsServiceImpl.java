package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Authentication;
import com.example.demo.model.LoginUser;
import com.example.demo.model.Role;
import com.example.demo.repository.mybatis.AuthenticationMapper;

import lombok.RequiredArgsConstructor;
/**
 * 
* カスタム認証サービス
*/
@Service
@RequiredArgsConstructor
public class LoginUserDatailsServiceImpl implements UserDetailsService {
/** DI */
private final AuthenticationMapper authenticationMapper;
@Override
public UserDetails loadUserByUsername(String username)
throws UsernameNotFoundException {
// 「認証テーブル」からデータを取得
Authentication authentication = authenticationMapper.selectByUsername(username);
// 対象データがあれば、UserDetailsの実装クラスを返す
if (authentication != null) {
// 対象データが存在する
// UserDetailsの実装クラスを返す
	return new LoginUser(authentication.getUsername(),
			authentication.getPassword(),
			getAuthorityList(authentication.getAuthority()),
			authentication.getDisplayname()
			);
} else {
// 対象データが存在しない
throw new UsernameNotFoundException(
username + " => 指定しているユーザー名は存在しません");
}
}

/**
* 権限情報をリストで取得する
*/
private List<GrantedAuthority> getAuthorityList(Role role) {
// 権限リスト
List<GrantedAuthority> authorities = new ArrayList<>();
// 列挙型からロールを取得
authorities.add(new SimpleGrantedAuthority(role.name()));
// ADMIN ロールの場合、USERの権限も付与
if (role == Role.ADMIN) {
authorities.add(
new SimpleGrantedAuthority(Role.USER.toString()));
}
return authorities;
}
}
//行数は一致しない
//20行目「@RequiredArgsConstructor」と23行目のfinalを使用しているフィールドから
//AuthenticationMapperがコンストラクタインジェクションされ、認証情報をDBから取得する
//処理が追加されています。
//29行目「authenticationMapper.selectByUsername(username);」で、usernameを使用して、
//DBから認証情報を取得します。
//35～38行目で「認証テーブル」から取得したデータを利用して、ユーザーの認証情報を表す
//UserDetails実装クラスを作成し、戻り値として返します。
//UsernameNotFoundExceptionは、ユーザーが見つからなかったことを示す例外です。
//9行目で呼んでいる「getAuthorityListメソッド」は、特定のユーザーの権限情報に基づいて、
//そのユーザーが持つ権限をリスト形式で返します。
//21行目で使用する「Role」はリストA.23で作成した列挙型のRoleになります。
//23行目「GrantedAuthority」は、Spring Securityが提供する「権限」を表すために使用されるイ
//ンターフェースです。
//25行目「SimpleGrantedAuthority」は、Spring Securityが提供するGrantedAuthorityインター
//フェースを簡易実装したクラスです。
//27行目～30行目で、ユーザーがADMIN権限（管理者）を持つ場合、USER権限もリストに追加
//しています。これにより、管理者ユーザーはADMIN権限、USER権限の両方を持ちます（図A.15）