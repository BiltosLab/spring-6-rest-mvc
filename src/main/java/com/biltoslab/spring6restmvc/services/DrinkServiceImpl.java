package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.Drink;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {
    @Override
    public Drink getDrinkById(UUID id) {
        log.debug("Getting drink by id: " + id.toString()+ "In DrinkServiceImpl");
        return Drink.builder()
                .id(id)
                .version(1)
                .drinkName("Kenza")
                .drinkStyle(DrinkStyle.SOFT_DRINK)
                .upc("123456")
                .price(new BigDecimal("35"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
