package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.mappers.DrinkMapper;
import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
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
    public DrinkDTO saveNewDrink(DrinkDTO drinkdto) {
        return drinkMapper.DrinkToDrinkDTO(drinkRepository.save(drinkMapper.DrinkDTOToDrink(drinkdto)));
    }

    @Override
    public Optional<DrinkDTO> updateDrink(UUID id, DrinkDTO drink) {
        AtomicReference<Optional<DrinkDTO>> atomicReference = new AtomicReference<>();

        drinkRepository.findById(id).ifPresentOrElse(foundDrink -> {
            foundDrink.setDrinkName(drink.getDrinkName());
            foundDrink.setDrinkStyle(drink.getDrinkStyle());
            foundDrink.setPrice(drink.getPrice());
            foundDrink.setUpc(drink.getUpc());
            foundDrink.setQuantityOnHand(drink.getQuantityOnHand());

            atomicReference.set(Optional.of(drinkMapper.DrinkToDrinkDTO(drinkRepository.save(foundDrink))));
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {
        if (drinkRepository.existsById(id)) {
            drinkRepository.deleteById(id);
            return true;
        }
            return false;
    }

    @Override
    public Optional<DrinkDTO> PatchDrink(UUID id, DrinkDTO drink) {
        AtomicReference<Optional<DrinkDTO>> atomicReference = new AtomicReference<>();

        drinkRepository.findById(id).ifPresentOrElse(foundDrink -> {
            if (StringUtils.hasText(drink.getDrinkName())) {
                foundDrink.setDrinkName(drink.getDrinkName());
            }
            if (drink.getDrinkStyle() != null) {
                foundDrink.setDrinkStyle(drink.getDrinkStyle());
            }
            if (drink.getPrice() != null) {
            foundDrink.setPrice(drink.getPrice());
            }
            if (StringUtils.hasText(drink.getUpc())) {
            foundDrink.setUpc(drink.getUpc());
            }
            if (drink.getQuantityOnHand() != null) {
                foundDrink.setQuantityOnHand(drink.getQuantityOnHand());
            }
            if (drink.getDrinkStyle() != null) {
                foundDrink.setDrinkStyle(drink.getDrinkStyle());
            }
            atomicReference.set(Optional.of(drinkMapper.DrinkToDrinkDTO(drinkRepository.save(foundDrink))));
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }
}




