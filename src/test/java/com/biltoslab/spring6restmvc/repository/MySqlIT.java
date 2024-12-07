package com.biltoslab.spring6restmvc.repository;


import com.biltoslab.spring6restmvc.entities.Drink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@ActiveProfiles("localmysql")
public class MySqlIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:9");


    @Autowired
    DrinkRepository drinkRepository;

    @Test
    void testListDrinks() {
        List<Drink> drinks = drinkRepository.findAll();
        assertThat(drinks.size()).isGreaterThan(0);

    }
}
