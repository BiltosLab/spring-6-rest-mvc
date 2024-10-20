package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {

    private Map<UUID, DrinkDTO> drinkMap;

    public DrinkServiceImpl() {
        drinkMap = new HashMap<>();

        DrinkDTO drink1 = DrinkDTO.builder()
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
        DrinkDTO drink2 = DrinkDTO.builder()
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
        DrinkDTO drink3 = DrinkDTO.builder()
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
    public List<DrinkDTO> listDrinks() {
        return new ArrayList<>(drinkMap.values());
    }


    @Override
    public Optional<DrinkDTO> getDrinkById(UUID id) {
        log.debug("Getting drink by id: " + id.toString()+ "In DrinkServiceImpl");
        return Optional.of(drinkMap.get(id));
    }

    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        DrinkDTO savedDrink = DrinkDTO.builder()
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
    public Optional<DrinkDTO> updateDrink(UUID id, DrinkDTO drink) {
        DrinkDTO existingDrink = drinkMap.get(id);

        existingDrink.setDrinkName(drink.getDrinkName());
        existingDrink.setUpc(drink.getUpc());
        existingDrink.setQuantityOnHand(drink.getQuantityOnHand());
        existingDrink.setPrice(drink.getPrice());
        drinkMap.put(id, existingDrink);
        return Optional.of(existingDrink);
    }

    @Override
    public Boolean deleteById(UUID id) {
        drinkMap.remove(id);
        return true;
    }

    @Override
    public Optional<DrinkDTO> PatchDrink(UUID id, DrinkDTO drink) {
        DrinkDTO DrinkToPatch = drinkMap.get(id);

        if (StringUtils.hasText(drink.getDrinkName())) {
            DrinkToPatch.setDrinkName(drink.getDrinkName());
        }
        if (StringUtils.hasText(drink.getUpc())) {
            DrinkToPatch.setUpc(drink.getUpc());
        }
        if (drink.getPrice() != null) {
            DrinkToPatch.setPrice(drink.getPrice());
        }
        if (drink.getQuantityOnHand() != null) {
            DrinkToPatch.setQuantityOnHand(drink.getQuantityOnHand());
        }
        if (drink.getDrinkStyle() != null) {
            DrinkToPatch.setDrinkStyle(drink.getDrinkStyle());
        }
        drinkMap.put(id, DrinkToPatch);
        return Optional.of(DrinkToPatch);
    }
}
