package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.services.DrinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DrinkController {
    private final DrinkService drinkService;
    public static final String DRINK_PATH = "/api/v1/drink";
    public static final String DRINK_PATH_ID = DRINK_PATH + "/{drinkId}";

    @PatchMapping(DRINK_PATH_ID)
    public ResponseEntity<Drink> PatchDrink(@PathVariable("drinkId") UUID Id, @RequestBody Drink drink) {
        drinkService.PatchDrink(Id,drink);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(DRINK_PATH_ID)
    public ResponseEntity<Drink> deleteDrink(@PathVariable("drinkId") UUID Id) {
        drinkService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(DRINK_PATH_ID)
    public ResponseEntity<Drink> UpdateDrink(@PathVariable("drinkId") UUID Id,@RequestBody Drink drink) {
        drinkService.updateDrink(Id, drink);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(DRINK_PATH)
    public ResponseEntity<Drink> saveNewDrink(@RequestBody Drink drink) {
        Drink savedDrink = drinkService.saveNewDrink(drink);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/drink/" + savedDrink.getId().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }



    @GetMapping(DRINK_PATH)
    public List<Drink> listDrinks() {
        return drinkService.listDrinks();
    }

    @GetMapping(DRINK_PATH_ID)
    public Drink getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("Getting drink by id: {} In DrinkController", drinkId.toString());
        return drinkService.getDrinkById(drinkId).orElseThrow(NotFoundException::new);
    }
}
