package com.biltoslab.spring6restmvc.entities;

import com.biltoslab.spring6restmvc.model.DrinkStyle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Drink {
    @Id
    @UuidGenerator
    @Column(length = 36, nullable = false,columnDefinition = "varchar",updatable = false)
    private UUID id;
    @Version
    private Integer version;
    private String drinkName;
    private DrinkStyle drinkStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
