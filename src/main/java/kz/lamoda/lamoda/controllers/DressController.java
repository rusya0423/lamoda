package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.models.Category;
import kz.lamoda.lamoda.models.Dress;
import kz.lamoda.lamoda.models.Image;
import kz.lamoda.lamoda.repositories.ImageRepository;
import kz.lamoda.lamoda.services.CategoryService;
import kz.lamoda.lamoda.services.DressService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@AllArgsConstructor
@RequestMapping("/dresses")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DressController {
    private DressService dressService;
    private CategoryService categoryService;
    private ImageRepository imageRepository;



    @GetMapping
    public String index(Model model) {
        model.addAttribute("dresses", dressService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "dresses/index";
    }

    @PostMapping("/create")
    public String store(@RequestParam String name,
                        @RequestParam int quantity,
                        @RequestParam String description,
                        @RequestParam MultipartFile image,
                        @RequestParam Double price,
                        @RequestParam Long categoryId)  throws Exception {
        Category category = categoryService.findById(categoryId);
        if (category != null) {



            String fileName = StringUtils.cleanPath(image.getOriginalFilename());


            fileName = System.currentTimeMillis() + "_" + fileName;
            Path targetLocation = Paths.get("var/tmp")
                    .toAbsolutePath().normalize().resolve(fileName);
            Files.copy(image.getInputStream(), targetLocation);


            Dress dress = Dress.builder().active(true)
                    .name(name)
                    .quantity(quantity)
                    .description(description)
                    .price(price)
                    .category(category)
                    .build();
             dressService.save(dress);

            Image image1 = Image.builder().dress(dress).path(fileName).main(true).build();
            imageRepository.save(image1);
            return "redirect:/dresses";
        }
        return "redirect:/dresses?error=ERROR";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        Dress dress = dressService.findById(id);
        if (dress != null) {
            model.addAttribute("dress", dress);
            model.addAttribute("categories", categoryService.findAll());
            return "dresses/update";
        }
        return "redirect:/index";
    }

    @PostMapping("/update/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam String name,
                       @RequestParam int quantity,
                       @RequestParam String description,
                       @RequestParam Double price){
    Dress dress = dressService.findById(id);
    if(dress != null){
        dress.setName(name);
        dress.setQuantity(quantity);
        dress.setDescription(description);
        dress.setPrice(price);
        dressService.update(dress);
        return "redirect:/dresses/update/" + id;
    }
    return "redirect:/dresses/update/" + id + "?error=password_error";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {
        Dress dress = dressService.findById(id);
        if (dress != null) {
            model.addAttribute("dress", dress);
            return "dresses/delete";
        }
        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String destroy(@PathVariable Long id){
        dressService.deleteById(id);
        return "redirect:/dresses";
    }


}
