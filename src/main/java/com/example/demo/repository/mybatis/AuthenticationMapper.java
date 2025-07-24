package com.example.demo.repository.mybatis;
/*
 * 作成者粂井「認証テーブルのデータ操作」メソッド記述のインターフェース
 */
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Authentication;
@Mapper
public interface AuthenticationMapper {
/**
* ユーザー名でログイン情報を取得します。
*/
Authentication selectByUsername(String username);
}