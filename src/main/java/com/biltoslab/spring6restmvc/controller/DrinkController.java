package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.services.DrinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/drink")
public class DrinkController {
    private final DrinkService drinkService;

    @DeleteMapping("{drinkId}")
    public ResponseEntity<Drink> deleteDrink(@PathVariable("drinkId") UUID Id) {
        drinkService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{beerId}")
    public ResponseEntity<Drink> UpdateDrink(@PathVariable("beerId") UUID Id,@RequestBody Drink drink) {
        drinkService.updateDrink(Id, drink);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<Drink> saveNewDrink(@RequestBody Drink drink) {
        Drink savedDrink = drinkService.saveNewDrink(drink);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/drink/" + savedDrink.getId().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Drink> listDrinks() {
        return drinkService.listDrinks();
    }

    @RequestMapping(value = "{drinkId}",method = RequestMethod.GET)
    public Drink getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("Getting drink by id: {} In DrinkController", drinkId.toString());
        return drinkService.getDrinkById(drinkId);
    }
}
