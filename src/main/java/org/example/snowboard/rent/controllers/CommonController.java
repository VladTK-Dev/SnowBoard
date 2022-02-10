package org.example.snowboard.rent.controllers;

import org.example.snowboard.rent.model.Snowboard;
import org.example.snowboard.rent.service.ISnowboardService;
import org.example.snowboard.rent.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class CommonController {

    @Autowired
    ISnowboardService carService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("snowboardList", this.carService.getAllSnowboards());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "main";
    }

    @RequestMapping(value = "/snowboard/create", method = RequestMethod.GET)
    public String create() {

        return "snowboard_create";
    }

    @RequestMapping(value = "/snowboard/create", method = RequestMethod.POST)
    public String store(@RequestParam String name, @RequestParam double price) {

        carService.addSnowboard(new Snowboard(name, price));

        return "redirect:/main";
    }

    @GetMapping(value = "/snowboard/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Optional<Snowboard> carBox = carService.getSnowboard(id);

        if (carBox.isEmpty()){
            return "redirect:/main";
        }

        carService.deleteSnowboard(carBox.get());

        return "redirect:/main";
    }
}
