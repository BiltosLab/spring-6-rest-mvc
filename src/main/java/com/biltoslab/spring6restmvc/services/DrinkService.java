package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.Drink;

import java.util.UUID;

public interface DrinkService {
    Drink getDrinkById(UUID id);


}
