package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**作成者粂井
 * テスト用に各機能へのリンクだけ持つMenuのコントローラー
 * 本番環境には不要
 */
@Controller
@RequestMapping("/")
public class MenuController {

    /**
     * メニュー画面を表示する
     */
    @GetMapping
    public String showMenu() {
        // templatesフォルダ配下のmenu.htmlに遷移
        return "menu";
    }
}

