package com.diazmic.VinylDatabaseAPI.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Artist {
    @Id
    @EqualsAndHashCode.Include
    private int id;

    private String name;
}
