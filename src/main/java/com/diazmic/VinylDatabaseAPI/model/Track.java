package com.diazmic.VinylDatabaseAPI.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Track {

    @Id
    @EqualsAndHashCode.Include
    private int trackID;

    private String title;

    private String length;


}
