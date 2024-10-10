package com.biltoslab.spring6restmvc.repository;

import com.biltoslab.spring6restmvc.entities.Drink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DrinkRepositoryTest {


    @Autowired
    DrinkRepository drinkRepository;

    @Test
    void saveDrink() {
        Drink savedDrink = drinkRepository.save(
                Drink.builder()
                        .drinkName("Matrix")
                        .build()
        );
        assertThat(savedDrink).isNotNull();
        assertThat(savedDrink.getId()).isNotNull();

    }

}