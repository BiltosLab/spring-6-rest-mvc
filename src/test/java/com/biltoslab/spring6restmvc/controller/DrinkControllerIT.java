package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.mappers.DrinkMapper;
import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class DrinkControllerIT {

    @Autowired
    DrinkController drinkController;
    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    DrinkMapper drinkMapper;

    @Test
    void updateDrink() {
        Drink drink = drinkRepository.findAll().getFirst();
        DrinkDTO drinkDTO = drinkMapper.DrinkToDrinkDTO(drink);
        drinkDTO.setId(null);
        drinkDTO.setVersion(null);
        final String name = "UPDATED DRINK";
        drinkDTO.setDrinkName(name);

       ResponseEntity<DrinkDTO> responseEntity = drinkController.UpdateDrink(drink.getId(),drinkDTO);
       assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));

        Drink updatedDrink = drinkRepository.findById(drink.getId()).get();
        assertThat(updatedDrink.getDrinkName()).isEqualTo(name);


    }

    @Rollback
    @Transactional
    @Test
    void saveNewDrink() {
        DrinkDTO drink = DrinkDTO.builder().drinkName("Pepsi").drinkStyle(DrinkStyle.SOFT_DRINK).quantityOnHand(1111).upc("123").build();

        ResponseEntity<DrinkDTO> responseEntity =drinkController.saveNewDrink(drink);
        System.out.println(drink.toString());


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);
        System.out.println(savedUUID.toString());
        Drink savedDrink = drinkRepository.findById(savedUUID).get();
        assertThat(savedDrink).isNotNull();
    }


    @Test
    void testListDrinks(){
         List<DrinkDTO> dtos = drinkController.listDrinks();
         assertThat(dtos.size()).isEqualTo(3);
    }
    @Rollback
    @Transactional
    @Test
    void testEmptyList(){
        drinkRepository.deleteAll();
        List<DrinkDTO> dtos = drinkController.listDrinks();
        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testGetDrinkById(){
        Drink drink = drinkRepository.findAll().get(0);
        DrinkDTO dto = drinkController.getDrinkById(drink.getId());
        assertThat(dto).isNotNull();

    }
    @Test
    void testGetDrinkByIdNotFound(){
        assertThrows(NotFoundException.class , () -> {
            drinkController.getDrinkById(UUID.randomUUID());
        });
    }
}