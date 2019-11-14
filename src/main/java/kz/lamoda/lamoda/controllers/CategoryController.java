package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.models.Category;
import kz.lamoda.lamoda.models.Dress;
import kz.lamoda.lamoda.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping
    public String index(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "categories/index";
    }

    @PostMapping("/create")
    public String store(@RequestParam String name){
        categoryService.save(Category.builder().name(name).build());
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        Category category = categoryService.findById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "categories/update";
        }
        return "redirect:/index";
    }

    @PostMapping("/update/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam String name){
        Category category = categoryService.findById(id);
        if(category != null){
            category.setName(name);
            categoryService.update(category);
            return "redirect:/categories/update/" + id;
        }
        return "redirect:/categories/update/" + id + "?error=password_error";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {
        Category category = categoryService.findById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "categories/delete";
        }
        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String destroy(@PathVariable Long id){
        categoryService.detete(id);
        return "redirect:/categories";
    }





}
