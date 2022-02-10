package org.example.snowboard.rent.controllers;

import org.example.snowboard.rent.service.ICartService;
import org.example.snowboard.rent.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add/{carId}")
    public String addBookToCart(@PathVariable Integer carId) {
        this.cartService.addCarToCart(carId);
        return "redirect:/main";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart",
                this.sessionObject.getCart());
        model.addAttribute("sum", this.sessionObject.getCart().getSum());
        model.addAttribute("logged", this.sessionObject.isLogged());

        return "cart";
    }
}
