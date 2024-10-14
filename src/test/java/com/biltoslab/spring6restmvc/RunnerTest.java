package com.biltoslab.spring6restmvc;

import com.biltoslab.spring6restmvc.repository.CustomerRepository;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class RunnerTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DrinkRepository drinkRepository;

    Runner runner;


    @BeforeEach
    void setUp() {
        runner = new Runner(customerRepository, drinkRepository);
    }

    @Test
    void run() throws Exception {
        runner.run();

        assertThat(drinkRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}