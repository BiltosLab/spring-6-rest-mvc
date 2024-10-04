package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.services.DrinkService;
import com.biltoslab.spring6restmvc.services.DrinkServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.HashMap;
import java.util.Map;
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
    ArgumentCaptor<Drink> drinkArgumentCaptor;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testPatchDrink() throws Exception {
        Drink drink = drinkServiceImpl.listDrinks().getFirst();

        Map<String,Object> drinkMap = new HashMap<>();
        drinkMap.put("drinkName","New Name");



        mockMvc.perform(patch("/api/v1/drink/"+drink.getId())
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
        Drink drink = drinkServiceImpl.listDrinks().getFirst();

        mockMvc.perform(delete("/api/v1/drink/" + drink.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(drinkService).deleteById(argumentCaptor.capture());

        assertThat(drink.getId()).isEqualTo(argumentCaptor.getValue());
    }

    @Test
    void TestUpdateDrink() throws Exception {
        Drink drink = drinkServiceImpl.listDrinks().getFirst();

        mockMvc.perform(put("/api/v1/drink/" + drink.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isNoContent());

        verify(drinkService).updateDrink(any(UUID.class), any(Drink.class));

    }

    @Test
    void TestCreateDrink() throws Exception {
        Drink drink = drinkServiceImpl.listDrinks().getFirst();
        drink.setId(null);
        drink.setVersion(null);

        given(drinkService.saveNewDrink(any(Drink.class))).willReturn(drinkServiceImpl.listDrinks().get(1));

        mockMvc.perform(post("/api/v1/drink")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));


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