package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.models.*;
import kz.lamoda.lamoda.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private DressService dressService;
    private CategoryService categoryService;
    private ClientService clientService;
    private OrderService orderService;
    private DressOrderService dressOrderService;


    @Autowired
    public CartController(DressService dressService, CategoryService categoryService, ClientService clientService, OrderService orderService, DressOrderService dressOrderService) {
        this.dressService = dressService;
        this.categoryService = categoryService;
        this.clientService = clientService;
        this.orderService = orderService;
        this.dressOrderService = dressOrderService;
    }

    @GetMapping
    public String index(HttpSession session, Model model){
        model.addAttribute("total", total(session));
        return "cart/index";
    }

     @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id, HttpSession session){
        if(session.getAttribute("cart")==null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(dressService.findById(id),1));
            session.setAttribute("cart", cart);
        }
        else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id, cart);
            if(index == -1){
                cart.add(new Item(dressService.findById(id), 1));
            }else{
                int quantity = cart.get(index).getQuantity()+1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
     }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExist(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String edit(HttpSession session, HttpServletRequest request){
        String [] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for(int i = 0; i < cart.size(); i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }



    @GetMapping("/checkout")
    public String checkout(HttpSession session, Authentication authentication){
        if(session.getAttribute("username")==null) {
            return "redirect:/profile";
        }else {
            Order order = new Order();
            Client currentClient = clientService.findByUserName(authentication.getName());
            order.setClient(currentClient);
            order.setActive(true);
            order.setOrderStatus(true);
            order.setCreatedAt(new Date());
           orderService.save(order);

            List<Item> cart = (List<Item>) session.getAttribute("cart");
            for(Item item : cart){
                DressOrders dressOrders = new DressOrders();
                dressOrders.setDress(dressService.findById(item.getDress().getId()));
                dressOrders.setPrice(item.getDress().getPrice());
                dressOrders.setQuantity(item.getQuantity());
                dressOrderService.save(dressOrders);
                Dress dress = dressService.findById(item.getDress().getId());
                dress.setQuantity(dress.getQuantity()-item.getQuantity());
                dressService.save(dress);
            }
            session.removeAttribute("cart");
            return "orders/thanks";
        }
    }




     private int isExist(Long id, List<Item> cart){
        for(int i = 0; i < cart.size(); i++ ){
            if(cart.get(i).getDress().getId() == id){
                return i;
            }
        }
        return -1;
     }

     private double total(HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double s = 0;
        for(Item item : cart){
            s += item.getQuantity()*item.getDress().getPrice();
        }
        return s;
     }
}
