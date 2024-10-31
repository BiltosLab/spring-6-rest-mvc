package com.biltoslab.spring6restmvc.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
public class DrinkDTO {
    private UUID id;
    private Integer version;
    @NotNull
    @NotBlank
    private String drinkName;
    @NotNull
    private DrinkStyle drinkStyle;

    @NotBlank
    @NotNull
    private String upc;
    private Integer quantityOnHand;
    @NotNull
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
