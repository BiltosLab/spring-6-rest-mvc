package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class DrinkControllerIT {

    @Autowired
    DrinkController drinkController;
    @Autowired
    DrinkRepository drinkRepository;

    @Test
    void TestListDrink(){
         List<DrinkDTO> dtos = drinkController.listDrinks();



         assertThat(dtos.size()).isEqualTo(3);
    }
    @Test
    void TestEmptyListDrink(){
        drinkRepository.deleteAll();
        List<DrinkDTO> dtos = drinkController.listDrinks();



        assertThat(dtos.size()).isEqualTo(0);
    }

}