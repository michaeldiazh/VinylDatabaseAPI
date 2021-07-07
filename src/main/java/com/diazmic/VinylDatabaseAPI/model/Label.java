package com.diazmic.VinylDatabaseAPI.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;



@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Label {
    @Id
    @EqualsAndHashCode.Include
    private int id;

    private String name;

}
