package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.models.Category;
import kz.lamoda.lamoda.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GuestController {
    private CategoryService categoryService;
    @Autowired
    public GuestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/s_category")
    public String guestCotegories(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "/s_category";
    }
}
