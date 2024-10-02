package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.controller.DrinkController;
import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {

    private Map<UUID, Drink> drinkMap;

    public DrinkServiceImpl() {
        drinkMap = new HashMap<>();

        Drink drink1 = Drink.builder()
                .id(UUID.randomUUID())
                .version(1)
                .drinkName("Kenza")
                .drinkStyle(DrinkStyle.SOFT_DRINK)
                .upc("123456")
                .price(new BigDecimal("35"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Drink drink2 = Drink.builder()
                .id(UUID.randomUUID())
                .version(1)
                .drinkName("Rani")
                .drinkStyle(DrinkStyle.JUICE)
                .upc("789654")
                .price(new BigDecimal("25"))
                .quantityOnHand(155)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Drink drink3 = Drink.builder()
                .id(UUID.randomUUID())
                .version(1)
                .drinkName("AL-Maraa'i")
                .drinkStyle(DrinkStyle.MILK)
                .upc("654321")
                .price(new BigDecimal("50"))
                .quantityOnHand(100)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        drinkMap.put(drink1.getId(), drink1);
        drinkMap.put(drink2.getId(), drink2);
        drinkMap.put(drink3.getId(), drink3);

    }


    @Override
    public List<Drink> listDrinks() {
        return new ArrayList<>(drinkMap.values());
    }


    @Override
    public Drink getDrinkById(UUID id) {
        log.debug("Getting drink by id: " + id.toString()+ "In DrinkServiceImpl");
        return drinkMap.get(id);
    }

    @Override
    public Drink saveNewDrink(Drink drink) {
        Drink savedDrink = Drink.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .drinkName(drink.getDrinkName())
                .drinkStyle(drink.getDrinkStyle())
                .upc(drink.getUpc())
                .quantityOnHand(drink.getQuantityOnHand())
                .price(drink.getPrice())
                .build();
        drinkMap.put(savedDrink.getId(), savedDrink);

        return savedDrink;
    }

    @Override
    public void updateDrink(UUID id, Drink drink) {
        Drink existingDrink = drinkMap.get(id);

        existingDrink.setDrinkName(drink.getDrinkName());
        existingDrink.setUpc(drink.getUpc());
        existingDrink.setQuantityOnHand(drink.getQuantityOnHand());
        existingDrink.setPrice(drink.getPrice());
        drinkMap.put(id, existingDrink);
    }

    @Override
    public void deleteById(UUID id) {
        drinkMap.remove(id);
    }
}
