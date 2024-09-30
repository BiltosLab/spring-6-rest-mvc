package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.services.DrinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/drink")
public class DrinkController {
    private final DrinkService drinkService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Drink> listDrinks() {
        return drinkService.listDrinks();
    }

    @RequestMapping(value = "{drinkId}",method = RequestMethod.GET)
    public Drink getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("Getting drink by id: {}In DrinkController", drinkId.toString());
        return drinkService.getDrinkById(drinkId);
    }
}
