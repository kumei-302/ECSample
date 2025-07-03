package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

/*作成者　粂井
*管理者用の商品操作用コントローラー
*/
@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    // 商品管理トップページ
    @GetMapping
    public String showProductManagement(Model model) {
        model.addAttribute("product", productService.getAll());
        return "admin/product_management";
    }

    //商品追加フォーム表示
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("isEdit", false);
        return "admin/product_form";
    }

    // 商品追加処理	
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, 
                           RedirectAttributes redirectAttributes) {
        try {
            if (productService.existsByProductName(product.getProductName())) {
                redirectAttributes.addFlashAttribute("error", "この商品名は既に登録されています。");
                return "redirect:/admin/product/add";
            }
            
            productService.save(product);
            redirectAttributes.addFlashAttribute("success", "商品を追加しました。");
            return "redirect:/admin/product";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "商品の追加に失敗しました。");
            return "redirect:/admin/product/add";
        }
    }

    //商品編集フォーム表示
    @GetMapping("/edit/{ProductId}")
    public String showEditProductForm(@PathVariable Integer ProductId, Model model,
                                    RedirectAttributes redirectAttributes) {
        return productService.findByProductId(ProductId)
            .map(product -> {
                model.addAttribute("product", product);
                model.addAttribute("isEdit", true);
                return "admin/product_form";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("error", "商品が見つかりません。");
                return "redirect:/admin/product";
            });
    }

    //商品更新処理
    @PostMapping("/edit/{ProductId}")
    public String updateProduct(@PathVariable Integer ProductId, 
                              @ModelAttribute Product product,
                              RedirectAttributes redirectAttributes) {
        try {
            // IDが一致することを確認
            product.setProductId(ProductId);
            
            // 名前の重複チェック（自分以外で同じ名前があるかチェック）
            productService.findByProductId(ProductId).ifPresent(existingProduct -> {
                if (!existingProduct.getProductName().equals(product.getProductName()) 
                    && productService.existsByProductName(product.getProductName())) {
                    throw new RuntimeException("この商品名は既に使用されています。");
                }
            });
            
            productService.save(product);
            redirectAttributes.addFlashAttribute("success", "商品を更新しました。");
            return "redirect:/admin/product";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/product/edit/" + ProductId;
        }
    }

    //商品削除処理
    @PostMapping("/delete/{ProductId}")
    public String deleteProduct(@PathVariable Integer ProductId, 
                              RedirectAttributes redirectAttributes) {
        try {
            if (productService.findByProductId(ProductId).isPresent()) {
                productService.deleteByProductId(ProductId);
                redirectAttributes.addFlashAttribute("success", "商品を削除しました。");
            } else {
                redirectAttributes.addFlashAttribute("error", "商品が見つかりません。");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "商品の削除に失敗しました。");
        }
        return "redirect:/admin/product";
    }

    //商品検索
    @GetMapping("/search")
    public String searchProducts(@RequestParam(required = false) String keyword, 
                               Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("product", productService.searchByProductName(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("product", productService.getAll());
        }
        return "admin/product_management";
    }
}