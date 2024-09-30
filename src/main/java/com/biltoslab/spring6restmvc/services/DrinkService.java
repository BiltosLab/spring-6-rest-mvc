package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.Drink;

import java.util.List;
import java.util.UUID;

public interface DrinkService {
    List<Drink> listDrinks();

    Drink getDrinkById(UUID id);


}
