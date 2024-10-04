package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.services.DrinkService;
import com.biltoslab.spring6restmvc.services.DrinkServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DrinkController.class)
class DrinkControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DrinkService drinkService;
    DrinkServiceImpl drinkServiceImpl = new DrinkServiceImpl();

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void TestCreateDrink() throws JsonProcessingException {
        Drink drink = drinkServiceImpl.listDrinks().getFirst();
        System.out.println(objectMapper.writeValueAsString(drink));
    }

    @Test
    void testListDrinks() throws Exception {
        given(drinkService.listDrinks()).willReturn(drinkServiceImpl.listDrinks());

        mockMvc.perform(get("/api/v1/drink")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(3)));

    }


    @Test
    void getDrinkById() throws Exception {
        Drink testDrink = drinkServiceImpl.listDrinks().getFirst();
        given(drinkService.getDrinkById(testDrink.getId())).willReturn(testDrink);

        mockMvc.perform(get("/api/v1/drink/" + testDrink.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(testDrink.getId().toString())))
                .andExpect(jsonPath("$.drinkName",is(testDrink.getDrinkName())));
    }
}