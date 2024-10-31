package com.biltoslab.spring6restmvc.repository;

import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DrinkRepositoryTest {


    @Autowired
    DrinkRepository drinkRepository;
    @Test
    void saveDrinkLongName() {
        assertThrows(ConstraintViolationException.class, () -> {
            Drink savedDrink = drinkRepository.save(
                    Drink.builder()
                            .drinkName("Matrix02131456789Matrix02131456789Matrix02131456789Matrix02131456789Matrix02131456789Matrix02131456789Matrix02131456789")
                            .build()
            );
            drinkRepository.flush();
        });


    }
    @Test
    void saveDrink() {
        Drink savedDrink = drinkRepository.save(
                Drink.builder()
                        .drinkName("Matrix")
                        .drinkStyle(DrinkStyle.SOFT_DRINK)
                        .upc("1231231")
                        .price(new BigDecimal(11))
                        .build()
        );
        drinkRepository.flush();

        assertThat(savedDrink).isNotNull();
        assertThat(savedDrink.getId()).isNotNull();

    }

}