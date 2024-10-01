package com.biltoslab.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
public class Customer {
    private String customerName;
    private UUID id;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
