package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileContoller {
    private ClientService clientService;

    @Autowired
    public ProfileContoller(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public String index(Model model, HttpSession session){
        return "user/profile";
    }


}
