package com.biltoslab.spring6restmvc;


import com.biltoslab.spring6restmvc.entities.Customer;
import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import com.biltoslab.spring6restmvc.repository.CustomerRepository;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {


    private final CustomerRepository customerRepository;
    private final DrinkRepository drinkRepository;



    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = Customer.builder().customerName("Ahmad").build();
        Customer customer2 = Customer.builder().customerName("Jafaar").build();
        Customer customer3 = Customer.builder().customerName("Mustafa").build();
        Drink drink1 = Drink.builder().drinkName("Kenza").drinkStyle(DrinkStyle.SOFT_DRINK).quantityOnHand(33).upc("10100").build();
        Drink drink2 = Drink.builder().drinkName("Cola").drinkStyle(DrinkStyle.SOFT_DRINK).quantityOnHand(11).upc("120").build();
        Drink drink3 = Drink.builder().drinkName("Matrix").drinkStyle(DrinkStyle.SOFT_DRINK).quantityOnHand(99).upc("1030").build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        drinkRepository.save(drink1);
        drinkRepository.save(drink2);
        drinkRepository.save(drink3);
    }
}
