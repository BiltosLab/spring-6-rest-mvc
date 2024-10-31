package com.biltoslab.spring6restmvc;


import com.biltoslab.spring6restmvc.entities.Customer;
import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import com.biltoslab.spring6restmvc.repository.CustomerRepository;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {


    private final CustomerRepository customerRepository;
    private final DrinkRepository drinkRepository;



    @Override
    public void run(String... args) throws Exception {
        System.out.println("Filling Data");
        Customer customer1 = Customer.builder().customerName("Marah").build();
        Customer customer2 = Customer.builder().customerName("Hadeel").build();
        Customer customer3 = Customer.builder().customerName("Mustafa").build();
        Drink drink1 = Drink.builder().drinkName("Kenza").drinkStyle(DrinkStyle.SOFT_DRINK).quantityOnHand(33).upc("10100").price(new BigDecimal("11.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Drink drink2 = Drink.builder().drinkName("Cola").drinkStyle(DrinkStyle.SOFT_DRINK).quantityOnHand(11).upc("120").price(new BigDecimal("11.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now()).build();
        Drink drink3 = Drink.builder().drinkName("Matrix").drinkStyle(DrinkStyle.SOFT_DRINK).quantityOnHand(99).upc("1030").price(new BigDecimal("11.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now()).build();
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        System.out.println("SIZE OF CUSTOMERS " + customerRepository.count());
        drinkRepository.save(drink1);
        drinkRepository.save(drink2);
        drinkRepository.save(drink3);
    }
}
