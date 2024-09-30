package com.biltoslab.spring6restmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DrinkControllerTest {

    @Autowired
    DrinkController drinkController;


    @Test
    void getDrinkById() {
        System.out.println(drinkController.getDrinkById(UUID.randomUUID()));
    }
}