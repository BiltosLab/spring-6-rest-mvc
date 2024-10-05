package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.Drink;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DrinkService {
    List<Drink> listDrinks();

    Optional<Drink> getDrinkById(UUID id);


    Drink saveNewDrink(Drink drink);
    void updateDrink(UUID id,Drink drink);

    void deleteById(UUID id);

    void PatchDrink(UUID id, Drink drink);
}
