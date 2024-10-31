package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.mappers.DrinkMapper;
import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class DrinkControllerIT {

    @Autowired
    DrinkController drinkController;
    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    DrinkMapper drinkMapper;
    @Autowired
    ObjectMapper objectMapper;
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testPatchDrinkBadName() throws Exception {
        Drink drink = drinkRepository.findAll().getFirst();

        Map<String,Object> drinkMap = new HashMap<>();
        drinkMap.put("drinkName","New Name1234567890Name1234567890Name1234567890Name1234567890Name1234567890Name1234567890Name1234567890Name1234567890Name1234567890Name1234567890");


        MvcResult result =  mockMvc.perform(patch(DrinkController.DRINK_PATH_ID,drink.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(drinkMap)))
                .andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }


    @Rollback
    @Transactional
    @Test
    void PatchDrink() {
        Drink drink = drinkRepository.findAll().getFirst();
        DrinkDTO drinkDTO = drinkMapper.DrinkToDrinkDTO(drink);
        drinkDTO.setId(null);
        drinkDTO.setVersion(null);
        final String name = "UPDATED DRINK";
        drinkDTO.setDrinkName(name);

        ResponseEntity<DrinkDTO> responseEntity = drinkController.PatchDrink(drink.getId(),drinkDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));

        Drink updatedDrink = drinkRepository.findById(drink.getId()).get();
        assertThat(updatedDrink.getDrinkName()).isEqualTo(name);


    }
    @Test
    void PatchDrinkNotFound(){
        assertThrows(NotFoundException.class, ()->{
            drinkController.PatchDrink(UUID.randomUUID(),DrinkDTO.builder().build());
        });
    }

    @Test
    void TestDeleteDrinkNotFound(){
        assertThrows(NotFoundException.class, ()->{
            drinkController.deleteDrink(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void TestDeleteDrinkFound() {
        Drink drink = drinkRepository.findAll().getFirst();
        ResponseEntity<DrinkDTO> responseEntity = drinkController.deleteDrink(drink.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        assertThat(drinkRepository.findById(drink.getId()).isEmpty());
    }

    @Rollback
    @Transactional
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
    @Test
    void UpdateNotFound(){
        assertThrows(NotFoundException.class, ()->{
            drinkController.UpdateDrink(UUID.randomUUID(),DrinkDTO.builder().build());
        });
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