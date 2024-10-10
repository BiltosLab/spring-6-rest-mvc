package com.biltoslab.spring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    //@GeneratedValue(generator = "UUID",strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(length = 36, nullable = false,columnDefinition = "varchar",updatable = false)
    private UUID id;
    private String customerName;
    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
