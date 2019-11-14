package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.models.DressOrders;
import kz.lamoda.lamoda.services.DressOrderService;
import kz.lamoda.lamoda.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private DressOrderService dressOrderService;

    @Autowired
    public OrderController(OrderService orderService, DressOrderService dressOrderService) {
        this.orderService = orderService;
        this.dressOrderService = dressOrderService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("dressOrders", dressOrderService.findAll());
        return "/orders/index";
    }
}
