package com.data.session_07.controller;

import com.data.session_07.model.entity.Product;
import com.data.session_07.model.service.CategoryService;
import com.data.session_07.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "addProducts";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, Model model , RedirectAttributes redirectAttributes) {
        if (product.getProductName() == null || product.getCategoryId() == null ||
                product.getDescription() == null || product.getPrice() == null) {
            model.addAttribute("product",product);
            model.addAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            return "addProducts";
        } else if (productService.checkProductNameExists(product.getProductName())) {
            model.addAttribute("product",product);
            model.addAttribute("error", "Tên sản phẩm đã tồn tại.");
            return "addProducts";
        }
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Thêm mới sản phẩm thành công !");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product, Model model, RedirectAttributes redirectAttributes) {
        Product oldProduct = productService.findById(id);
        if (product.getProductName() == null || product.getCategoryId() == null ||
                product.getDescription() == null || product.getPrice() == null) {
            model.addAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            model.addAttribute("product", product);
            return "editProduct";
        } else if (productService.checkProductNameExists(product.getProductName()) && !oldProduct.getProductName().equalsIgnoreCase(product.getProductName())) {
            model.addAttribute("product",product);
            model.addAttribute("error", "Tên sản phẩm đã tồn tại.");
            return "addProducts";
        }
        product.setProductId(id); // Đảm bảo id không thay đổi
        redirectAttributes.addFlashAttribute("message","Cập nhật sản phẩm thành công !");
        productService.save(product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping
    public String listProduct(
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "asc") String sortOrder,
            Model model
    ) {
        List<Product> products = productService.searchAndFilterProducts(categoryId, searchTerm, sortOrder);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAll());

        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("sortOrder", sortOrder);

        return "listProducts";
    }
}
