package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.services.DrinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class DrinkController {
    private final DrinkService drinkService;

    public Drink getDrinkById(UUID id) {
        log.debug("Getting drink by id: " + id.toString()+ "In DrinkController");
        return drinkService.getDrinkById(id);
    }
}
