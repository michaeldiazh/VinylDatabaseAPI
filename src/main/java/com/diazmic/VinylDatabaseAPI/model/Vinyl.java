package com.diazmic.VinylDatabaseAPI.model;

import com.diazmic.VinylDatabaseAPI.service.formatter.IntegerFormatter;
import com.diazmic.VinylDatabaseAPI.service.formatter.StringFormatter;
import lombok.*;
import org.springframework.data.annotation.Id;
import java.util.Collection;
/*
    {
        obj: Vinyl

    }

 */
@Data
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vinyl {

    public enum Type{
        LP,
        EP
    }
    public enum Size{
       SEVEN_INCH,
       TWELVE_INCH
    }
    public enum RPM{
        THIRTY_THREE,
        FORTY_FIVE,
        SEVENTY_EIGHT
    }

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    private String catalogNumber;

    @NonNull
    @ToString.Include
    private String title;

    private Genre genre;

    @NonNull
    @ToString.Include
    private Artist artist;

    @ToString.Include
    private Label label;

    @NonNull
    private Type type;

    @NonNull
    private Size size;

    @NonNull
    private RPM rpm;

    @NonNull
    private Collection<Track> tracks;

    @NonNull
    private VinylInventory vinylInventory;

    public String getLength(){
        int length = 0;
        for(Track track : tracks){
            length += IntegerFormatter.formatSeconds(track.getLength());
        }
        return StringFormatter.formatSeconds(length);
    }

}
