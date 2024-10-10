package com.biltoslab.spring6restmvc.repository;

import com.biltoslab.spring6restmvc.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrinkRepository extends JpaRepository<Drink, UUID> {

}
