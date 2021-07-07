package com.diazmic.VinylDatabaseAPI.model;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@Builder
public class VinylInventory {
    @Id
    private String catalogNumber;

    private int vinylAmountInInventory;


}
