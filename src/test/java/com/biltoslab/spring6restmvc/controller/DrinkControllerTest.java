package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.services.DrinkService;
import com.biltoslab.spring6restmvc.services.DrinkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DrinkController.class)
class DrinkControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DrinkService drinkService;
    DrinkServiceImpl drinkServiceImpl;

    @BeforeEach
    void setUp() {
        drinkServiceImpl = new DrinkServiceImpl();
    }
    @Captor
    ArgumentCaptor<UUID> argumentCaptor;
    @Captor
    ArgumentCaptor<DrinkDTO> drinkArgumentCaptor;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testPatchDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().getFirst();
        given(drinkService.PatchDrink(any(),any())).willReturn(Optional.of(drink));

        Map<String,Object> drinkMap = new HashMap<>();
        drinkMap.put("drinkName","New Name");



        mockMvc.perform(patch(DrinkController.DRINK_PATH_ID,drink.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drinkMap)))
                .andExpect(status().isNoContent());

        verify(drinkService).PatchDrink(argumentCaptor.capture(),drinkArgumentCaptor.capture());
        assertThat(drink.getId()).isEqualTo(argumentCaptor.getValue());
        assertThat(drinkMap.get("drinkName")).isEqualTo(drinkArgumentCaptor.getValue().getDrinkName());


    }

    @Test
    void testDeleteDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().getFirst();
        given(drinkService.deleteById(any())).willReturn(true);
        mockMvc.perform(delete(DrinkController.DRINK_PATH_ID,drink.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(drinkService).deleteById(argumentCaptor.capture());

        assertThat(drink.getId()).isEqualTo(argumentCaptor.getValue());
    }

    @Test
    void TestUpdateDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().getFirst();
        given(drinkService.updateDrink(any(),any())).willReturn(Optional.of(drink));

        mockMvc.perform(put(DrinkController.DRINK_PATH_ID,drink.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isNoContent());

        verify(drinkService).updateDrink(any(UUID.class), any(DrinkDTO.class));

    }
    @Test
    void TestCreateDrinkNull() throws Exception {
        DrinkDTO drink = DrinkDTO.builder().build();

        given(drinkService.saveNewDrink(any(DrinkDTO.class))).willReturn(drinkServiceImpl.listDrinks().get(1));

       MvcResult mvcResult = mockMvc.perform(post(DrinkController.DRINK_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isBadRequest()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }
    @Test
    void TestCreateDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().getFirst();
        drink.setId(null);
        drink.setVersion(null);

        given(drinkService.saveNewDrink(any(DrinkDTO.class))).willReturn(drinkServiceImpl.listDrinks().get(1));

        mockMvc.perform(post(DrinkController.DRINK_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));


    }

    @Test
    void testListDrinks() throws Exception {
        given(drinkService.listDrinks()).willReturn(drinkServiceImpl.listDrinks());

        mockMvc.perform(get(DrinkController.DRINK_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(3)));

    }

    @Test
    void getDrinkByIdNotFound() throws Exception {

        given(drinkService.getDrinkById(any(UUID.class))).willReturn(Optional.empty());
        mockMvc.perform(get(DrinkController.DRINK_PATH_ID,UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDrinkById() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().getFirst();
        given(drinkService.getDrinkById(drink.getId())).willReturn(Optional.of(drink));

        mockMvc.perform(get(DrinkController.DRINK_PATH_ID,drink.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(drink.getId().toString())))
                .andExpect(jsonPath("$.drinkName",is(drink.getDrinkName())));
    }
}