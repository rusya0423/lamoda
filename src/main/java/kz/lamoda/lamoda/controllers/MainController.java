package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.models.Client;
import kz.lamoda.lamoda.models.Role;
import kz.lamoda.lamoda.services.ClientService;
import kz.lamoda.lamoda.services.DressService;
import kz.lamoda.lamoda.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class MainController {
    private ClientService clientService;
    private RoleService roleService;
    private DressService dressService;




    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }
    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        Client currentClient = clientService.findByUserName(authentication.getName());
        model.addAttribute("currentClient", currentClient);
        return "user/profile";
    }

    @GetMapping("/index")
    public String home(Model model, HttpSession session, Authentication authentication) {
        session.setAttribute("username", clientService.findByUserName(authentication.getName()));
        model.addAttribute("dresses", dressService.findAll());
        return "user/index";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String repassword){
    if(clientService.register(firstName, lastName, username, password, repassword) == null){
        return "redirect:/register?error=1";
    }
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "auth/403";
    }



}
