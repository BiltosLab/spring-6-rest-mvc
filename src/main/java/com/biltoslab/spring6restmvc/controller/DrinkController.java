package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.DrinkDTO;
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
    public ResponseEntity<DrinkDTO> PatchDrink(@PathVariable("drinkId") UUID Id, @RequestBody DrinkDTO drink) {
        drinkService.PatchDrink(Id,drink);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(DRINK_PATH_ID)
    public ResponseEntity<DrinkDTO> deleteDrink(@PathVariable("drinkId") UUID Id) {
        drinkService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(DRINK_PATH_ID)
    public ResponseEntity<DrinkDTO> UpdateDrink(@PathVariable("drinkId") UUID Id, @RequestBody DrinkDTO drink) {
        drinkService.updateDrink(Id, drink);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(DRINK_PATH)
    public ResponseEntity<DrinkDTO> saveNewDrink(@RequestBody DrinkDTO drink) {
        DrinkDTO savedDrink = drinkService.saveNewDrink(drink);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/drink/" + savedDrink.getId().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }



    @GetMapping(DRINK_PATH)
    public List<DrinkDTO> listDrinks() {
        return drinkService.listDrinks();
    }

    @GetMapping(DRINK_PATH_ID)
    public DrinkDTO getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("Getting drink by id: {} In DrinkController", drinkId.toString());
        return drinkService.getDrinkById(drinkId).orElseThrow(NotFoundException::new);
    }
}
