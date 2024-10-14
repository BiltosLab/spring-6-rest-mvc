package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.mappers.DrinkMapper;
import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Primary
@RequiredArgsConstructor
public class DrinkServiceJPA implements DrinkService {
    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;



    @Override
    public List<DrinkDTO> listDrinks() {
        return drinkRepository.findAll()
                .stream()
                .map(drinkMapper::DrinkToDrinkDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DrinkDTO> getDrinkById(UUID id) {
        return Optional.ofNullable(drinkMapper
                .DrinkToDrinkDTO(drinkRepository
                        .findById(id)
                        .orElse(null)));
    }

    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        return null;
    }

    @Override
    public void updateDrink(UUID id, DrinkDTO drink) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void PatchDrink(UUID id, DrinkDTO drink) {

    }
}
