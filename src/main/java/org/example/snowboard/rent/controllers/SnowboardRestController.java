package org.example.snowboard.rent.controllers;

import org.example.snowboard.rent.model.Snowboard;
import org.example.snowboard.rent.service.ISnowboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/snowboard")
public class SnowboardRestController {

    @Autowired
    ISnowboardService snowboardService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Snowboard> getSnowboardList() {

        return snowboardService.getAllSnowboards();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Snowboard getsnowboard(@PathVariable int id) {

        Optional<Snowboard> snowboardBox = snowboardService.getSnowboard(id);

        if(snowboardBox.isEmpty()) {
            return new Snowboard();
        }

        return snowboardBox.get();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Snowboard addsnowboard(@RequestBody Snowboard snowboard) {

        snowboardService.addSnowboard(snowboard);

        return snowboard;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Snowboard updatesnowboard(@PathVariable int id, @RequestBody Snowboard snowboard) {


        Optional<Snowboard> snowboardBox = snowboardService.getSnowboard(id);

        if(snowboardBox.isEmpty()) {
            return new Snowboard();
        }

        Snowboard snowboardFromBox = snowboardBox.get();

        snowboardFromBox.setName(snowboard.getName());
        snowboardFromBox.setPrice(snowboard.getPrice());

        snowboardService.updateSnowboard(snowboardFromBox);

        return snowboardFromBox;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletesnowboard(@PathVariable int id) {


        Optional<Snowboard> snowboardBox = snowboardService.getSnowboard(id);

        if(snowboardBox.isEmpty()) {
            return;
        }

        Snowboard snowboardFromBox = snowboardBox.get();

        snowboardService.deleteSnowboard(snowboardFromBox);
    }
}
