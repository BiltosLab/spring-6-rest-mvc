package com.biltoslab.spring6restmvc.mappers;


import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.model.DrinkDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DrinkMapper {
    Drink DrinkDTOToDrink(DrinkDTO dto);
    DrinkDTO DrinkToDrinkDTO(Drink drink);
}
