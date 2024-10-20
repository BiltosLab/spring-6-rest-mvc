package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.DrinkDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DrinkService {
    List<DrinkDTO> listDrinks();

    Optional<DrinkDTO> getDrinkById(UUID id);


    DrinkDTO saveNewDrink(DrinkDTO drink);
    Optional<DrinkDTO> updateDrink(UUID id, DrinkDTO drink);

    Boolean deleteById(UUID id);

    Optional<DrinkDTO> PatchDrink(UUID id, DrinkDTO drink);
}
